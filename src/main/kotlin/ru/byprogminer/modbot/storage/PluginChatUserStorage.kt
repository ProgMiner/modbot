package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Actor

interface PluginChatUserStorage: Storage {

    operator fun get(actor: Actor): PluginActorChatUserStorage
}
