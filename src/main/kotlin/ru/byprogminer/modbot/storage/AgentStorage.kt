package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.api.Chat
import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.User

interface AgentStorage: Storage {

    fun forUser(user: User): AgentUserStorage
    fun forChat(chat: Chat): ChatStorage
    fun forPlugin(plugin: Plugin): PluginAgentStorage
}
