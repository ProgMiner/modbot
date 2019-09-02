package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Agent
import ru.byprogminer.modbot.api.User

interface PluginChatStorage: Storage {

    operator fun get(user: User): PluginChatUserStorage
    operator fun get(agent: Agent): PluginAgentChatStorage
}
