package ru.byprogminer.modbot.api

import ru.byprogminer.modbot.Agent
import java.time.Duration
import java.time.MonthDay

/**
 * User impl have to has equals and hashCode impls based on id field
 */
interface User: Chat {

    /**
     * The "point of view" on this user
     */
    override val agent: Agent

    /**
     * Short name, e.g. first name
     */
    override val name: String
    val fullName: String
    val names: List<String>

    override val photo: Set<PhotoVariant>?
    val birthday: MonthDay?
    val birthdayYear: Int?

    val isOnline: Boolean
    val lastSeen: Duration?
    /**
     * Is this user able to communication, or it is banned/deleted/etc.
     */
    val isAvailable: Boolean

    /**
     * Text that can be used in message text for link to this user
     */
    fun link(caption: String?): String?

    /**
     * User objects must be able to use as a key
     */
    override fun equals(other: Any?): Boolean
    /**
     * User objects must be able to use as a key
     */
    override fun hashCode(): Int
}
