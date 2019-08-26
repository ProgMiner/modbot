package ru.byprogminer.modbot

import ru.byprogminer.modbot.utility.RunState

abstract class AbstractAgent: Agent {

    private val runState = RunState()

    override val isStarted: Boolean
        get() = runState.isStarted

    override fun start() = runState
        .tryStart(this::onStart)
        .ifNotRun { return }
        .waitForStop(this::onStop)

    override fun stop() = runState.stop()

    protected open fun onStart() {}
    protected open fun onStop() {}
}
