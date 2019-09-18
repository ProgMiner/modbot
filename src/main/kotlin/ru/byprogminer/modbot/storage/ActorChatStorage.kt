package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.User

interface ActorChatStorage: Storage {

    operator fun get(user: User): ActorChatUserStorage
    operator fun get(plugin: Plugin): PluginActorChatStorage
}
