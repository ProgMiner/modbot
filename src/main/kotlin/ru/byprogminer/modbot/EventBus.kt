package ru.byprogminer.modbot

import ru.byprogminer.modbot.event.Event
import java.time.Duration

interface EventBus {

    annotation class Handler

    val parsers: Map<Plugin, Map<Class<*>, Parser<*>>>

    val plugins: Map<Chat, Set<Plugin>>
    val excludedFeatures: Map<Chat, Map<Plugin, Set<Event>>>

    fun fireEvent(event: Event)
    fun scheduleCron(plugin: Plugin, duration: Duration, id: Any? = null)

    fun registerPlugin(chat: Chat, plugin: Plugin)
    fun unregisterPlugin(chat: Chat, plugin: Plugin)

    fun registerParser(plugin: Plugin, parser: Parser<*>)
    fun unregisterParser(plugin: Plugin, parser: Parser<*>)
}
