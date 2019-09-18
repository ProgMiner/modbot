package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.api.User

interface PluginActorChatStorage: Storage {

    operator fun get(user: User): PluginActorChatUserStorage
}
