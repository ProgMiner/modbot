package ru.byprogminer.modbot

import ru.byprogminer.modbot.message.Attachment
import java.awt.image.RenderedImage
import java.net.URI

interface AttachmentHandler {

    fun photo(image: RenderedImage): Attachment
    fun video(uri: URI): Attachment
    fun audio(uri: URI): Attachment
    fun document(uri: URI): Attachment
}
