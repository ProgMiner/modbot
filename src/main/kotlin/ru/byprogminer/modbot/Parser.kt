package ru.byprogminer.modbot

import ru.byprogminer.modbot.event.Event

interface Parser<I: Event> {

    val input: Class<I>
    val output: Set<Class<Event>>

    fun parse(input: I): Set<Event>
}
