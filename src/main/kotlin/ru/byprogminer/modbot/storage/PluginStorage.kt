package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Agent
import ru.byprogminer.modbot.api.Chat
import ru.byprogminer.modbot.api.User

interface PluginStorage: Storage {

    operator fun get(user: User): PluginUserStorage
    operator fun get(chat: Chat): PluginChatStorage
    operator fun get(agent: Agent): PluginAgentStorage
}
