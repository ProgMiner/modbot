package ru.byprogminer.modbot.utility

import java.util.concurrent.atomic.AtomicReference

class RunState {

    private enum class State {

        INITIAL, START, RUN, STOP
    }

    private val state = AtomicReference(State.INITIAL)

    val isStarted: Boolean
        get() = state.get() in setOf(State.START, State.RUN)

    @Synchronized
    fun tryStart(block: () -> Unit): RunState {
        if (state.get() != State.INITIAL) {
            return this
        }

        state.set(State.START)

        try {
            block()
        } finally {
            state.set(State.RUN)
        }

        return this
    }

    inline fun ifNotRun(block: () -> Unit): RunState {
        if (!isStarted) {
            block()
        }

        return this
    }

    fun waitForStop(block: () -> Unit) {
        while (true) {
            synchronized(this) {
                if (state.get() == State.RUN) {
                    return@synchronized
                }

                try {
                    block()
                } finally {
                    state.set(State.INITIAL)
                }

                return
            }

            Thread.yield()
        }
    }

    @Synchronized
    fun stop() {
        if (state.get() == State.RUN) {
            state.set(State.STOP)
        }
    }
}
