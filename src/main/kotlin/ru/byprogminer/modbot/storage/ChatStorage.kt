package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Actor
import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.User

interface ChatStorage: Storage {

    operator fun get(user: User): ChatUserStorage
    operator fun get(actor: Actor): ActorChatStorage
    operator fun get(plugin: Plugin): PluginChatStorage
}
