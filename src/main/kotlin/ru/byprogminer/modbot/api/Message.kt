package ru.byprogminer.modbot.api

import ru.byprogminer.modbot.api.message.Attachment

interface Message {

    val text: String?
    val attachments: List<Attachment>

    val reply: RemoteMessage?
    val forwarded: List<RemoteMessage>
}
