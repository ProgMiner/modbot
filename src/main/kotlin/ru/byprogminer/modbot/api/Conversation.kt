package ru.byprogminer.modbot.api

import ru.byprogminer.modbot.Actor

interface Conversation: Chat {

    /**
     * The "point of view" on this conversation
     */
    override val actor: Actor

    override var name: String
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
}
