package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.Chat

interface AgentUserStorage: Storage {

    fun forChat(chat: Chat): ChatUserStorage
    fun forPlugin(plugin: Plugin): PluginAgentUserStorage
}
