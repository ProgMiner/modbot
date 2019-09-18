package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Actor
import ru.byprogminer.modbot.api.Chat

interface PluginUserStorage: Storage {

    operator fun get(chat: Chat): PluginChatUserStorage
    operator fun get(actor: Actor): PluginActorUserStorage
}
