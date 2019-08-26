package ru.byprogminer.modbot

import ru.byprogminer.modbot.event.Event

interface Parser<I: Event> {

    val input: Class<out Event>
    val output: Set<Class<out Event>>

    fun parse(input: I): Set<Event>
}
