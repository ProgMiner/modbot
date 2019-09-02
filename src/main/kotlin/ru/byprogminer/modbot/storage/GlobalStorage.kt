package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Agent
import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.Chat
import ru.byprogminer.modbot.api.User

interface GlobalStorage: Storage {

    operator fun get(user: User): UserStorage
    operator fun get(chat: Chat): ChatStorage
    operator fun get(agent: Agent): AgentStorage
    operator fun get(plugin: Plugin): PluginStorage
}
