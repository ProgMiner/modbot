package ru.byprogminer.modbot

import ru.byprogminer.modbot.api.Chat
import ru.byprogminer.modbot.storage.GlobalStorage
import java.time.Duration

interface EventBus {

    @MustBeDocumented
    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Handler

    val storage: GlobalStorage

    fun<E: Event> fireEvent(event: E)
    fun scheduleCron(plugin: Plugin, duration: Duration, id: Any? = null)

    fun registerPlugin(chat: Chat, plugin: Plugin): Boolean
    fun unregisterPlugin(chat: Chat, plugin: Plugin): Boolean
    fun getRegisteredPlugins(chat: Chat): Set<Plugin>?

    fun<I: Event> registerParser(parser: Parser<I>): Boolean
    fun<I: Event> unregisterParser(parser: Parser<I>): Boolean
    fun getRegisteredParsers(): Set<Parser<*>>

    fun excludePluginFeature(chat: Chat, plugin: Plugin, feature: Class<out Event>): Boolean
    fun includePluginFeature(chat: Chat, plugin: Plugin, feature: Class<out Event>): Boolean
    fun getExcludedFeatures(chat: Chat, plugin: Plugin): Set<Class<out Event>>
}
