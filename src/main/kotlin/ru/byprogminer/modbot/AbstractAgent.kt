package ru.byprogminer.modbot

import ru.byprogminer.modbot.utility.RunState
import java.util.*
import java.util.stream.Collectors

abstract class AbstractAgent: Agent {

    private val runState = RunState()

    override val isStarted: Boolean
        get() = runState.isStarted

    override fun start() = runState
        .tryStart(this::onStart)
        .ifNotRun { return }
        .waitForStop(this::onStop)

    override fun stop() = runState.stop()

    override fun sendMessage(chat: Chat, message: List<Any?>) = sendMessage(chat,
        message.parallelStream().map(this::messageMapping).collect(Collectors.joining()))

    protected abstract fun sendMessage(chat: Chat, message: String)
    protected open fun mention(user: User): String = user.name

    protected open fun messageMapping(element: Any?): String = when (element) {
        is String -> element
        is User -> mention(element)
        else -> Objects.toString(element)
    }

    protected open fun onStart() {}
    protected open fun onStop() {}
}
