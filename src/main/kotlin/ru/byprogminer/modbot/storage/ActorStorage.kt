package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.Chat
import ru.byprogminer.modbot.api.User

interface ActorStorage: Storage {

    operator fun get(user: User): ActorUserStorage
    operator fun get(chat: Chat): ActorChatStorage
    operator fun get(plugin: Plugin): PluginActorStorage
}
