package ru.byprogminer.modbot.attachment

import java.time.Duration

interface Audio: Attachment {

    val id: Long
    val title: String
    val duration: Duration
    val url: String
}
