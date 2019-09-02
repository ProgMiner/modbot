package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Plugin

interface AgentChatUserStorage: Storage {

    operator fun get(plugin: Plugin): PluginAgentChatUserStorage
}
