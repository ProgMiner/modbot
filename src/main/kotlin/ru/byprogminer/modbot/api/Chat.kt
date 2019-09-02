package ru.byprogminer.modbot.api

import ru.byprogminer.modbot.Agent

interface Chat {

    /**
     * The "point of view" on this chat
     */
    val agent: Agent

    val name: String
    val photo: Set<PhotoVariant>?

    /**
     * Chat objects must be able to use as a key
     */
    override fun equals(other: Any?): Boolean
    /**
     * Chat objects must be able to use as a key
     */
    override fun hashCode(): Int
}
