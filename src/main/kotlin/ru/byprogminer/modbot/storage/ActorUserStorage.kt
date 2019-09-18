package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.Chat

interface ActorUserStorage: Storage {

    operator fun get(chat: Chat): ActorChatUserStorage
    operator fun get(plugin: Plugin): PluginActorUserStorage
}
