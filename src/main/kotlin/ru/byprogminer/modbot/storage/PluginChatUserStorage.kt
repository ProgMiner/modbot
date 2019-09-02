package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Agent

interface PluginChatUserStorage: Storage {

    operator fun get(agent: Agent): PluginAgentChatUserStorage
}
