package ru.byprogminer.modbot

import ru.byprogminer.modbot.api.AttachmentUploader
import ru.byprogminer.modbot.api.SelfUser

interface Agent {

    val user: SelfUser

    val isStarted: Boolean

    fun start()
    fun stop()

    fun uploadAttachment(): AttachmentUploader
}
