package ru.byprogminer.modbot

import ru.byprogminer.modbot.attachment.Attachment
import java.time.ZonedDateTime

interface Message {

    val chat: Chat
    val user: User

    val id: Long
    val date: ZonedDateTime

    val text: String
    val attachments: List<Attachment>

    val reply: Message?
    val forwarded: List<Message>

    val allMentionedUsers: List<User>
}
