package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Agent
import ru.byprogminer.modbot.Plugin

interface ChatUserStorage: Storage {

    operator fun get(agent: Agent): AgentChatUserStorage
    operator fun get(plugin: Plugin): PluginChatUserStorage
}
