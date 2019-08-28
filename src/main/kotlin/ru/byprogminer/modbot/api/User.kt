package ru.byprogminer.modbot.api

import ru.byprogminer.modbot.Agent
import java.time.Duration
import java.time.LocalDate

/**
 * User impl have to has equals and hashCode impls based on id field
 */
interface User {

    /**
     * The "point of view" on this user
     */
    val agent: Agent

    /**
     * Short name, e.g. first name
     */
    val name: String
    val fullName: String
    val names: List<String>

    val birthday: LocalDate?
    val photo: Set<PhotoVariant>?

    val isOnline: Boolean
    val lastSeen: Duration?
    /**
     * Is this user able to communication, or it is banned/deleted/etc.
     */
    val isAvailable: Boolean

    /**
     * Text that can be used in message text for link to this user
     */
    val link: String?

    /**
     * User objects must be able to use as a key
     */
    override fun equals(other: Any?): Boolean
    /**
     * User objects must be able to use as a key
     */
    override fun hashCode(): Int
}
