package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.api.Chat
import ru.byprogminer.modbot.api.User

interface PluginAgentStorage: Storage {

    fun forUser(user: User): PluginAgentUserStorage
    fun forChat(chat: Chat): PluginChatStorage
}
