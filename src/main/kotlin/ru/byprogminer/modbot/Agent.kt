package ru.byprogminer.modbot

interface Agent {

    val id: Long

    val isStarted: Boolean

    fun start()
    fun stop()

    fun kick(chat: Chat, user: User)
    fun sendMessage(chat: Chat, message: List<Any>)
}
