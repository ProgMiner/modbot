package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.User

interface ChatStorage: Storage {

    fun forUser(user: User): ChatUserStorage
    fun forPlugin(plugin: Plugin): PluginChatStorage
}
