package ru.byprogminer.modbot

import ru.byprogminer.modbot.utility.RunState
import java.time.ZonedDateTime
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

abstract class AbstractChat<U: User>: Chat<U> {

    private val runState = RunState()

    private val _plugins = mutableSetOf<Plugin>()

    private val cronExecutor = Executors.newSingleThreadScheduledExecutor()
    private val cronFutures = mutableMapOf<Plugin, ScheduledFuture<*>>()

    override val isRunning get() = runState.isStarted

    override val plugins: MutableSet<Plugin> by lazy { Collections.unmodifiableSet(_plugins) }

    override fun start() = runState
        .tryStart(this::onStart)
        .ifNotRun { return }
        .waitForStop {
            cronExecutor.shutdown()

            this.onStop()
        }

    override fun stop() = runState.stop()

    override fun registerPlugin(plugin: Plugin) {
        synchronized(_plugins) {
            require(!_plugins.contains(plugin)) { "plugin already registered" }

            plugin.cronPeriod?.also { period -> cronFutures[plugin] = cronExecutor
                .schedule({ plugin.cron(ZonedDateTime.now(), this) }, period.toNanos(), TimeUnit.NANOSECONDS) }

            _plugins.add(plugin)
            return@synchronized
        }
    }

    override fun unregisterPlugin(plugin: Plugin) {
        synchronized(_plugins) {
            if (!_plugins.contains(plugin)) {
                return
            }

            cronFutures[plugin]?.takeIf { !it.isCancelled }
                ?.also { it.cancel(false) }

            _plugins.remove(plugin)
            return@synchronized
        }
    }

    protected open fun onStart() = Unit
    protected open fun onStop() = Unit
}
