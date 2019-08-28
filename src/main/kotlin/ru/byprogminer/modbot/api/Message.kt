package ru.byprogminer.modbot.api

import ru.byprogminer.modbot.api.message.Attachment

interface Message {

    val text: String?
    val attachments: List<Attachment>

    val reply: Message?
    val forwarded: List<Message>
}
