package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.api.Chat
import ru.byprogminer.modbot.api.User

interface PluginActorStorage: Storage {

    operator fun get(user: User): PluginActorUserStorage
    operator fun get(chat: Chat): PluginActorChatStorage
}
