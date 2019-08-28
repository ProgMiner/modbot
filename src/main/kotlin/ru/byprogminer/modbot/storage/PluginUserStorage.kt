package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Agent
import ru.byprogminer.modbot.api.Chat

interface PluginUserStorage: Storage {

    fun forChat(chat: Chat): PluginChatUserStorage
    fun forAgent(agent: Agent): PluginAgentUserStorage
}
