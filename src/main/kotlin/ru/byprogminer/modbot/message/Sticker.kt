package ru.byprogminer.modbot.message

interface Sticker: Attachment {

    val id: Long
    val images: Set<Photo.Variant>
}
