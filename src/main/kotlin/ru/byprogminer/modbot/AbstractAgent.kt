package ru.byprogminer.modbot

import ru.byprogminer.modbot.api.AttachmentUploader
import ru.byprogminer.modbot.utility.RunState

abstract class AbstractAgent: Agent {

    abstract val attachmentUploader: AttachmentUploader

    private val runState = RunState()

    override val isStarted: Boolean
        get() = runState.isStarted

    override fun start() = runState
        .tryStart(this::onStart)
        .ifNotRun { return }
        .waitForStop(this::onStop)

    override fun stop() = runState.stop()

    override fun uploadAttachment() = attachmentUploader

    protected open fun onStart() {}
    protected open fun onStop() {}
}
