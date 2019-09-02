package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.Chat

interface AgentUserStorage: Storage {

    operator fun get(chat: Chat): AgentChatUserStorage
    operator fun get(plugin: Plugin): PluginAgentUserStorage
}
