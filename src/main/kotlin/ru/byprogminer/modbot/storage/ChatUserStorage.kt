package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Plugin

interface ChatUserStorage: Storage {

    fun forPlugin(plugin: Plugin): PluginChatUserStorage
}
