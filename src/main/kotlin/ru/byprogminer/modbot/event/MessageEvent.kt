package ru.byprogminer.modbot.event

import ru.byprogminer.modbot.Message

interface MessageEvent: UserEvent {

    val message: Message
}
