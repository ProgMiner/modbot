package ru.byprogminer.modbot.api

import java.time.ZonedDateTime

interface RemoteMessage: Message {

    val chat: Chat
    val user: User

    val date: ZonedDateTime

    val allMentionedUsers: List<User>

    fun edit(new: Message)
    fun remove()

    fun toNewMessage(): NewMessage
}