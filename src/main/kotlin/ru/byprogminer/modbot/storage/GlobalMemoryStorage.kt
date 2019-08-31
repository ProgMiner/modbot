package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Agent
import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.Chat
import ru.byprogminer.modbot.api.User
import java.util.WeakHashMap

open class GlobalMemoryStorage: MemoryStorage(), GlobalStorage {

    protected open inner class Accessor(
        private val plugin: Plugin? = null,
        private val agent: Agent? = null,
        private val chat: Chat? = null,
        private val user: User? = null
    ):
        PluginStorage, AgentStorage, ChatStorage, UserStorage,
        PluginAgentStorage, PluginChatStorage, PluginUserStorage,
        AgentUserStorage, ChatUserStorage, PluginAgentUserStorage,
        PluginChatUserStorage, Storage by storage(plugin, agent, chat, user) {

        override fun forPlugin(plugin: Plugin) = accessor(plugin, agent, chat, user)
        override fun forAgent(agent: Agent) = accessor(plugin, agent, chat, user)
        override fun forChat(chat: Chat) = accessor(plugin, agent, chat, user)
        override fun forUser(user: User) = accessor(plugin, agent, chat, user)
    }

    protected val storages = mutableMapOf<Plugin?, MutableMap<Agent?, MutableMap<Chat?, MutableMap<User?, MemoryStorage>>>>()
    private val accessors = WeakHashMap<Plugin?, MutableMap<Agent?, MutableMap<Chat?, MutableMap<User?, Accessor>>>>()

    override fun forPlugin(plugin: Plugin): PluginStorage = accessor(plugin = plugin)
    override fun forAgent(agent: Agent): AgentStorage = accessor(agent = agent)
    override fun forChat(chat: Chat): ChatStorage = accessor(chat = chat)
    override fun forUser(user: User): UserStorage = accessor(user = user)

    private fun storage(plugin: Plugin?, agent: Agent?, chat: Chat?, user: User?): MemoryStorage = storages
        .computeIfAbsent(plugin) { mutableMapOf() }.computeIfAbsent(agent) { mutableMapOf() }
        .computeIfAbsent(chat) { mutableMapOf() }.computeIfAbsent(user) { MemoryStorage() }

    private fun accessor(plugin: Plugin? = null, agent: Agent? = null, chat: Chat? = null, user: User? = null) =
        accessors.computeIfAbsent(plugin) { WeakHashMap() }.computeIfAbsent(agent) { WeakHashMap() }
            .computeIfAbsent(chat) { WeakHashMap() }.computeIfAbsent(user) { Accessor(plugin, agent, chat, user) }
}
