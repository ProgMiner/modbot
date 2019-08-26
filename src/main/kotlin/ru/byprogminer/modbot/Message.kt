package ru.byprogminer.modbot

import ru.byprogminer.modbot.message.Attachment
import java.time.ZonedDateTime

interface Message {

    val chat: Chat
    val userId: Long

    val id: Long
    val date: ZonedDateTime

    val text: String
    val attachments: List<Attachment>

    val reply: Message?
    val forwarded: List<Message>

    val allMentionedUserIds: List<Long>
}
