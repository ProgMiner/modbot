package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Agent
import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.Chat

interface UserStorage: Storage {

    fun forChat(chat: Chat): ChatUserStorage
    fun forAgent(agent: Agent): AgentUserStorage
    fun forPlugin(plugin: Plugin): PluginUserStorage
}
