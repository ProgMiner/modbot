package ru.byprogminer.modbot

interface Parser<I: Event> {

    val input: Class<I>

    fun parse(input: I): Set<Event>
}
