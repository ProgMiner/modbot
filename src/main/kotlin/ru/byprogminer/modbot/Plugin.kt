package ru.byprogminer.modbot

import java.time.ZonedDateTime

interface Plugin {

    fun init(eventBus: EventBus, chat: Chat) {}
    fun final(eventBus: EventBus, chat: Chat) {}

    fun cron(eventBus: EventBus, time: ZonedDateTime, id: Any?) {}
}
