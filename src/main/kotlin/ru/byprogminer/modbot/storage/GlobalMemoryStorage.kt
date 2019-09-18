package ru.byprogminer.modbot.storage

import ru.byprogminer.modbot.Actor
import ru.byprogminer.modbot.Plugin
import ru.byprogminer.modbot.api.Chat
import ru.byprogminer.modbot.api.User
import java.util.*

open class GlobalMemoryStorage: MemoryStorage(), GlobalStorage {

    protected open inner class Accessor(
        private val plugin: Plugin? = null,
        private val actor: Actor? = null,
        private val chat: Chat? = null,
        private val user: User? = null
    ):
        GlobalStorage, PluginStorage, ActorStorage, ChatStorage, UserStorage,
        PluginActorStorage, PluginChatStorage, PluginUserStorage, ActorChatStorage,
        ActorUserStorage, ChatUserStorage, PluginActorChatStorage, PluginActorUserStorage,
        ActorChatUserStorage, PluginChatUserStorage, PluginActorChatUserStorage,
        Storage by storage(plugin, actor, chat, user) {

        override operator fun get(plugin: Plugin) = accessor(plugin, actor, chat, user)
        override operator fun get(actor: Actor) = accessor(plugin, actor, chat, user)
        override operator fun get(chat: Chat) = accessor(plugin, actor, chat, user)
        override operator fun get(user: User) = accessor(plugin, actor, chat, user)
    }

    protected val storages = mutableMapOf<Plugin?, MutableMap<Actor?, MutableMap<Chat?, MutableMap<User?, MemoryStorage>>>>()
    private val accessors = WeakHashMap<Plugin?, MutableMap<Actor?, MutableMap<Chat?, MutableMap<User?, Accessor>>>>()

    override operator fun get(plugin: Plugin): PluginStorage = accessor(plugin = plugin)
    override operator fun get(actor: Actor): ActorStorage = accessor(actor = actor)
    override operator fun get(chat: Chat): ChatStorage = accessor(chat = chat)
    override operator fun get(user: User): UserStorage = accessor(user = user)

    private fun storage(plugin: Plugin?, actor: Actor?, chat: Chat?, user: User?): MemoryStorage = storages
        .computeIfAbsent(plugin) { mutableMapOf() }.computeIfAbsent(actor) { mutableMapOf() }
        .computeIfAbsent(chat) { mutableMapOf() }.computeIfAbsent(user) { MemoryStorage() }

    private fun accessor(plugin: Plugin? = null, actor: Actor? = null, chat: Chat? = null, user: User? = null) =
        accessors.computeIfAbsent(plugin) { WeakHashMap() }.computeIfAbsent(actor) { WeakHashMap() }
            .computeIfAbsent(chat) { WeakHashMap() }.computeIfAbsent(user) { Accessor(plugin, actor, chat, user) }
}
