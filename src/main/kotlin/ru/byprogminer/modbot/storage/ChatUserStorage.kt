package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Actor
import ru.byprogminer.modbot.Plugin

interface ChatUserStorage: Storage {

    operator fun get(actor: Actor): ActorChatUserStorage
    operator fun get(plugin: Plugin): PluginChatUserStorage
}
