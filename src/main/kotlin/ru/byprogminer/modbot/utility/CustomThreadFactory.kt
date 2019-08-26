package ru.byprogminer.modbot.utility

import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory

internal class CustomThreadFactory(
    private val threadFactory: ThreadFactory,
    private val block: Thread.() -> Unit
): ThreadFactory {

    companion object {

        fun daemon(threadFactory: ThreadFactory = Executors.defaultThreadFactory()) =
            CustomThreadFactory(threadFactory) { isDaemon = true }
    }

    override fun newThread(r: Runnable): Thread = threadFactory.newThread(r).apply(block)
}
