package ru.byprogminer.modbot

/**
 * User impl have to has equals and hashCode impls based on id field
 */
interface User {

    val id: Long

    val name: String
    val firstName: String
    val lastName: String
}
