package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.api.Chat

interface PluginActorUserStorage: Storage {

    operator fun get(chat: Chat): PluginActorChatUserStorage
}
