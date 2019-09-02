package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.api.Chat
import ru.byprogminer.modbot.api.User

interface PluginAgentStorage: Storage {

    operator fun get(user: User): PluginAgentUserStorage
    operator fun get(chat: Chat): PluginAgentChatStorage
}
