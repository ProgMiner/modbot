package ru.byprogminer.modbot.api.message

interface Document: Attachment {

    val id: Long
    val title: String
    val url: String
    val size: Int
}
