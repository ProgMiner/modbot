package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Actor
import ru.byprogminer.modbot.api.User

interface PluginChatStorage: Storage {

    operator fun get(user: User): PluginChatUserStorage
    operator fun get(actor: Actor): PluginActorChatStorage
}
