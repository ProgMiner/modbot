package ru.byprogminer.modbot.api

import ru.byprogminer.modbot.Actor
import java.time.ZonedDateTime

interface RemoteMessage: Message {

    /**
     * The "point of view" on this remote message
     */
    val actor: Actor

    val chat: Chat?
    val author: User

    val date: ZonedDateTime

    val allMentionedUsers: List<User>

    fun edit(new: NewMessage)
    fun remove(forAll: Boolean = true)

    fun cloneToNew(): NewMessage
}
