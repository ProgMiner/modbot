package ru.byprogminer.modbot.attachment

interface Sticker: Attachment {

    val id: Long
    val images: Set<Photo.Variant>
}
