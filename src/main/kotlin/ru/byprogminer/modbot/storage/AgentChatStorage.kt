package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.User

interface AgentChatStorage: Storage {

    operator fun get(user: User): AgentChatUserStorage
    operator fun get(plugin: Plugin): PluginAgentChatStorage
}
