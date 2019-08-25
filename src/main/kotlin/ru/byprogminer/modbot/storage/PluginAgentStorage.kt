package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Chat
import ru.byprogminer.modbot.User

interface PluginAgentStorage: Storage {

    fun forUser(user: User): PluginAgentUserStorage
    fun forChat(chat: Chat): PluginChatStorage
}
