package ru.byprogminer.modbot.api

import ru.byprogminer.modbot.api.message.Attachment
import java.awt.image.RenderedImage
import java.net.URI

interface AttachmentUploader {

    fun photo(image: RenderedImage): Attachment
    fun video(uri: URI): Attachment
    fun audio(uri: URI): Attachment
    fun document(uri: URI): Attachment
}
