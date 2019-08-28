package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Agent
import ru.byprogminer.modbot.api.Chat
import ru.byprogminer.modbot.api.User

interface PluginStorage: Storage {

    fun forUser(user: User): PluginUserStorage
    fun forChat(chat: Chat): PluginChatStorage
    fun forAgent(agent: Agent): PluginAgentStorage
}
