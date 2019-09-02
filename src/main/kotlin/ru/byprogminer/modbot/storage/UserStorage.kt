package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Agent
import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.Chat

interface UserStorage: Storage {

    operator fun get(chat: Chat): ChatUserStorage
    operator fun get(agent: Agent): AgentUserStorage
    operator fun get(plugin: Plugin): PluginUserStorage
}
