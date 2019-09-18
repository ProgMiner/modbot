package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Actor
import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.Chat

interface UserStorage: Storage {

    operator fun get(chat: Chat): ChatUserStorage
    operator fun get(actor: Actor): ActorUserStorage
    operator fun get(plugin: Plugin): PluginUserStorage
}
