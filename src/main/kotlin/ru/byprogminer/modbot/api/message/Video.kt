package ru.byprogminer.modbot.api.message

import ru.byprogminer.modbot.api.PhotoVariant
import java.time.Duration

interface Video: Attachment {

    val id: Long
    val title: String
    val description: String
    val thumbnails: Set<PhotoVariant>
    val duration: Duration
}
