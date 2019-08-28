package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.api.User

interface PluginChatStorage: Storage {

    fun forUser(user: User): PluginChatUserStorage
}
