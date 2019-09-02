package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Agent
import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.User

interface ChatStorage: Storage {

    operator fun get(user: User): ChatUserStorage
    operator fun get(agent: Agent): AgentChatStorage
    operator fun get(plugin: Plugin): PluginChatStorage
}
