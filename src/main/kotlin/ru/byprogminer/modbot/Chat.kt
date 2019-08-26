package ru.byprogminer.modbot

/**
 * Chat impl have to has equals and hashCode impls based on id and agent fields
 */
interface Chat {

    val id: Long
    val agent: Agent

    val title: String
    val owner: User
}
