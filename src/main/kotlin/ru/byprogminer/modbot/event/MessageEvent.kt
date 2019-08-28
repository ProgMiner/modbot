package ru.byprogminer.modbot.event

import ru.byprogminer.modbot.api.Message

interface MessageEvent: UserEvent {

    val message: Message
}
