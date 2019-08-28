package ru.byprogminer.modbot.api.message

import ru.byprogminer.modbot.api.PhotoVariant

interface Photo: Attachment {

    val id: Long
    val description: String
    val variants: Set<PhotoVariant>
}
