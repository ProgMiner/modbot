package ru.byprogminer.modbot.event

import ru.byprogminer.modbot.Message

interface MessageEvent: Event {

    val message: Message
}
