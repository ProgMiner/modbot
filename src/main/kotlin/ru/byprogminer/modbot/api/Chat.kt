package ru.byprogminer.modbot.api

import ru.byprogminer.modbot.Agent

interface Chat {

    /**
     * The "point of view" on this chat
     */
    val agent: Agent

    var title: String
    var image: Set<PhotoVariant>?
    var pinnedMessage: Message?

    val owner: User
    /**
     * Rank -> User. Lower rank - higher permissions
     */
    val admins: Map<Int, User>
    val members: Set<User>

    fun joinUser(user: User)
    fun kickUser(user: User)

    fun appointAdmin(user: User, rank: Int)
    fun dismissAdmin(user: User)

    /**
     * Chat objects must be able to use as a key
     */
    override fun equals(other: Any?): Boolean
    /**
     * Chat objects must be able to use as a key
     */
    override fun hashCode(): Int
}
