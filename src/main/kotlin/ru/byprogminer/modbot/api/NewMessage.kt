package ru.byprogminer.modbot.api

interface NewMessage: Message {

    fun sendTo(chat: Chat): RemoteMessage
    fun sendTo(user: User): RemoteMessage
}