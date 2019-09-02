package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.api.Chat

interface PluginAgentUserStorage: Storage {

    operator fun get(chat: Chat): PluginAgentChatUserStorage
}
