package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Plugin

interface ActorChatUserStorage: Storage {

    operator fun get(plugin: Plugin): PluginActorChatUserStorage
}
