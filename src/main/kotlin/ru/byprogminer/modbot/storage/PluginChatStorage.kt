package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.User

interface PluginChatStorage: Storage {

    fun forUser(user: User): PluginChatUserStorage
}
