package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Agent
import ru.byprogminer.modbot.api.Chat
import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.User

interface GlobalStorage: Storage {

    fun forUser(user: User): UserStorage
    fun forChat(chat: Chat): ChatStorage
    fun forAgent(agent: Agent): AgentStorage
    fun forPlugin(plugin: Plugin): PluginStorage
}
