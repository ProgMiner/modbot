package ru.byprogminer.modbot

import ru.byprogminer.modbot.api.Chat
import ru.byprogminer.modbot.event.Event
import ru.byprogminer.modbot.utility.CustomThreadFactory
import java.lang.reflect.Method
import java.time.Duration
import java.time.ZonedDateTime
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantReadWriteLock
import java.util.stream.Collectors
import java.util.stream.IntStream
import kotlin.concurrent.read
import kotlin.concurrent.write

abstract class AbstractEventBus(scheduleThreads: Int): EventBus {

    private val parsers = mutableMapOf<Class<out Event>, MutableSet<Parser<*>>>()
    private val parsersLock = ReentrantReadWriteLock()

    private val plugins = mutableMapOf<Chat, MutableSet<Plugin>>()
    private val pluginsLock = ReentrantReadWriteLock()

    private val handlers = mutableMapOf<Class<out Event>, MutableMap<Plugin, MutableSet<(Event) -> Unit>>>()
    private val handlersLock = ReentrantReadWriteLock()

    private val excludedFeatures = mutableMapOf<Chat, MutableMap<Plugin, MutableSet<Class<out Event>>>>()
    private val excludedFeaturesLock = ReentrantReadWriteLock()

    private val scheduleExecutor = Executors.newScheduledThreadPool(scheduleThreads, CustomThreadFactory.daemon())
    private val eventExecutor = Executors.newCachedThreadPool(CustomThreadFactory.daemon())

    override fun<E: Event> fireEvent(event: E) {
        pluginsLock.read { plugins[event.source]?.ifEmpty { null } }
            ?: return // If the chat isn't used any plugin - return

        // Parse event
        eventExecutor.submit {
            parsersLock.read {
                parsers[event::class.java]?.parallelStream()
                    ?.flatMap { @Suppress("UNCHECKED_CAST") (it as Parser<E>).parse(event).parallelStream() }
                    ?.forEach { eventExecutor.submit { fireEvent(it) } }
            }
        }

        // Invoke plugins
        val allowedPlugins = pluginsLock.read { plugins[event.source]?.parallelStream()
            ?.filter { excludedFeaturesLock.read { excludedFeatures[event.source]?.get(it)?.contains(event::class.java) }
                ?.run { !this } ?: true }?.collect(Collectors.toSet()) } ?: emptySet<Plugin>()

        handlersLock.read {
            handlers[event::class.java]?.entries?.parallelStream()
                ?.filter { (plugin, _) -> allowedPlugins.contains(plugin) }
                ?.flatMap { (_, handlers) -> handlers.parallelStream() }
                ?.forEach { eventExecutor.submit { it(event) } }
        }
    }

    override fun scheduleCron(plugin: Plugin, duration: Duration, id: Any?) {
        scheduleExecutor.schedule({ plugin.cron(this, ZonedDateTime.now(), id) },
            duration.toNanos(), TimeUnit.NANOSECONDS)
    }

    override fun registerPlugin(chat: Chat, plugin: Plugin) = pluginsLock.write {
        val plugins = plugins.computeIfAbsent(chat) { mutableSetOf() }

        if (plugins.contains(plugin)) {
            return@write false
        }

        plugin.init(this, chat)
        Arrays.stream(plugin::class.java.declaredMethods).parallel()
            .map(convertHandler(plugin)).filter { it != null }.map { it!! } // Dumb Kotlin >_<"
            .forEach { (clazz, handler) -> handlersLock.write { handlers.computeIfAbsent(clazz) { mutableMapOf() }
                .computeIfAbsent(plugin) { mutableSetOf() }.add(handler) } }

        return@write plugins.add(plugin) // Always true
    }

    private fun convertHandler(plugin: Plugin) = fun(method: Method): Pair<Class<Event>, (Event) -> Unit>? {
        val warn = if (method.isAnnotationPresent(EventBus.Handler::class.java)) { msg: String ->
            System.err.println("Method ${method.declaringClass.name}.${method.name} " +
                    "is annotated ${EventBus.Handler::class.java.name} but $msg") // TODO logging
        } else { _ -> }

        if (method.parameterCount !in 1..2) {
            warn("it's parameters count isn't 1 or 2")
            return null
        }

        val parameterTypes = method.parameterTypes
        val eventBusParameter =
            if (parameterTypes.size == 2) IntStream
                .range(0, parameterTypes.size).parallel()
                .mapToObj { Pair(it, parameterTypes[it]) }
                .filter { (_, it) -> it.isAssignableFrom(EventBus::class.java) }
                .findFirst().orElseGet { null }?.first
                ?: null.let {
                    warn("it has 2 parameters and hasn't parameter of the ${EventBus::class.java} type")
                    return null
                }
            else null

        val eventParameter = eventBusParameter?.let { (it + 1) % 2 } ?: 0
        if (!Event::class.java.isAssignableFrom(parameterTypes[eventParameter])) {
            warn("it hasn't ${Event::class.java} parameter")
            return null
        }

        // Dumb Kotlin >_<" [2]
        val handler: (Event) -> Unit = when (eventBusParameter) {
            0 -> { event: Event -> method.isAccessible = true; method.invoke(plugin, this, event) }
            1 -> { event: Event -> method.isAccessible = true; method.invoke(plugin, event, this) }
            else -> { event: Event -> method.isAccessible = true; method.invoke(plugin, event) }
        }

        @Suppress("UNCHECKED_CAST")
        return parameterTypes[eventParameter] as Class<Event> to handler
    }

    override fun unregisterPlugin(chat: Chat, plugin: Plugin) = pluginsLock.write {
        val plugins = plugins[chat] ?: return@write false

        if (!plugins.contains(plugin)) {
            return@write false
        }

        plugin.final(this, chat)
        handlersLock.write { handlers.values.parallelStream().forEach { it.remove(plugin) } }

        return@write plugins.remove(plugin) // Always true
    }

    override fun getRegisteredPlugins(chat: Chat): Set<Plugin>? = pluginsLock
        .read { plugins[chat]?.let { Collections.unmodifiableSet(it) } }

    override fun<I: Event> registerParser(parser: Parser<I>) = parsersLock
        .write { parsers.computeIfAbsent(parser.input) { mutableSetOf() }.add(parser) }

    override fun<I: Event> unregisterParser(parser: Parser<I>) = parsersLock
        .write { parsers[parser.input]?.remove(parser) } ?: false

    override fun getRegisteredParsers(): Set<Parser<*>> = parsersLock
        .read { parsers.values.parallelStream().flatMap { it.parallelStream() } }.collect(Collectors.toSet())

    override fun excludePluginFeature(chat: Chat, plugin: Plugin, feature: Class<out Event>) = excludedFeaturesLock
        .write { excludedFeatures.computeIfAbsent(chat) { mutableMapOf() }
            .computeIfAbsent(plugin) { mutableSetOf() }.add(feature) }

    override fun includePluginFeature(chat: Chat, plugin: Plugin, feature: Class<out Event>) = excludedFeaturesLock
        .write { excludedFeatures[chat]?.get(plugin)?.remove(feature) } ?: false

    override fun getExcludedFeatures(chat: Chat, plugin: Plugin): Set<Class<out Event>> = excludedFeaturesLock
        .read { excludedFeatures[chat]?.get(plugin)?.let { Collections.unmodifiableSet(it) } } ?: emptySet()
}
