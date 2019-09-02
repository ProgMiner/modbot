package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.Chat
import ru.byprogminer.modbot.api.User

interface AgentStorage: Storage {

    operator fun get(user: User): AgentUserStorage
    operator fun get(chat: Chat): AgentChatStorage
    operator fun get(plugin: Plugin): PluginAgentStorage
}
