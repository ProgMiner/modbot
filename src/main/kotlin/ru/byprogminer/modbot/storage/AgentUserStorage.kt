package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Chat
import ru.byprogminer.modbot.Plugin

interface AgentUserStorage: Storage {

    fun forChat(chat: Chat): ChatUserStorage
    fun forPlugin(plugin: Plugin): PluginAgentUserStorage
}