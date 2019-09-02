package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Agent
import ru.byprogminer.modbot.api.Chat

interface PluginUserStorage: Storage {

    operator fun get(chat: Chat): PluginChatUserStorage
    operator fun get(agent: Agent): PluginAgentUserStorage
}
