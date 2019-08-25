package ru.byprogminer.modbot.message

interface Photo: Attachment {

    data class Variant(
        val url: String,
        val width: Int,
        val height: Int
    )

    val id: Long
    val description: String
    val variants: Set<Variant>
}
