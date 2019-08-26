package ru.byprogminer.modbot

import ru.byprogminer.modbot.message.Attachment

interface Agent {

    val id: Long

    val isStarted: Boolean

    fun start()
    fun stop()

    fun kick(chat: Chat, user: User)
    fun sendMessage(chat: Chat, message: List<Any?>, attachments: List<Attachment>)

    fun handleAttachment(): AttachmentHandler
    fun getChat(id: Long): Chat
    fun getUser(id: Long): User
}
