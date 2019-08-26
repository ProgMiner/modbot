package ru.byprogminer.modbot

import ru.byprogminer.modbot.event.Event
import java.time.Duration

interface EventBus {

    @MustBeDocumented
    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Handler

    val excludedFeatures: Map<Chat, MutableMap<Plugin, MutableSet<Class<out Event>>>>

    fun<E: Event> fireEvent(event: E)
    fun scheduleCron(plugin: Plugin, duration: Duration, id: Any? = null)

    fun registerPlugin(chat: Chat, plugin: Plugin)
    fun unregisterPlugin(chat: Chat, plugin: Plugin): Boolean
    fun getRegisteredPlugins(chat: Chat): Set<Plugin>?

    fun<I: Event> registerParser(parser: Parser<I>)
    fun<I: Event> unregisterParser(parser: Parser<I>): Boolean
    fun getRegisteredParsers(): Set<Parser<*>>
}
