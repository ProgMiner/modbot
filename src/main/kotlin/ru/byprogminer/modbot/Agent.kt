package ru.byprogminer.modbot

import ru.byprogminer.modbot.message.Attachment

interface Agent {

    val id: Long

    val isStarted: Boolean

    fun start()
    fun stop()

    fun kick(chat: Chat, userId: Long)
    fun sendMessage(chat: Chat, message: List<Any?>, attachments: List<Attachment>)

    fun handleAttachment(): AttachmentHandler
    fun getUser(id: Long): User
}
