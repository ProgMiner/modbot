package ru.byprogminer.modbot.api.message

import ru.byprogminer.modbot.api.PhotoVariant

interface Sticker: Attachment {

    val id: Long
    val images: Set<PhotoVariant>
}
