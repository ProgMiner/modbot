package ru.byprogminer.modbot.message

import java.time.Duration

interface Video: Attachment {

    val id: Long
    val title: String
    val description: String
    val thumbnails: Set<Photo.Variant>
    val duration: Duration
}
