package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.api.User

interface PluginAgentChatStorage: Storage {

    operator fun get(user: User): PluginAgentChatUserStorage
}
