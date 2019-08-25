package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Chat

interface PluginAgentUserStorage: Storage {

    fun forChat(chat: Chat): PluginChatUserStorage
}
