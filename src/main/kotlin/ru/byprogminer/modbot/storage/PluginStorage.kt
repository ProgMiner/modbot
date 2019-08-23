package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Chat
import ru.byprogminer.modbot.User

interface PluginStorage: Storage {

    fun forUser(user: User): PluginUserStorage
    fun forChat(chat: Chat<*>): PluginChatStorage
}
