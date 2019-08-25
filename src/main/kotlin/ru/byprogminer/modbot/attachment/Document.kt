package ru.byprogminer.modbot.attachment

interface Document: Attachment {

    val id: Long
    val title: String
    val url: String
    val size: Int
}
