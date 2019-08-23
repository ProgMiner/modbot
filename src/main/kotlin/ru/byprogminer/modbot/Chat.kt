package ru.byprogminer.modbot

interface Chat<U: User> {

    val plugins: Set<Plugin>
    val isRunning: Boolean

    fun start()
    fun stop()

    fun<P: Plugin> getPlugin(plugin: Class<P>): P? = plugins.parallelStream()
        .filter(plugin::isInstance).map(plugin::cast).findAny().orElse(null)

    fun registerPlugin(plugin: Plugin)
    fun unregisterPlugin(plugin: Plugin)

    fun kick(user: U)
    fun sendMessage(msg: List<*>)
}
