package ru.byprogminer.modbot

interface Chat<U: User> {

    val plugins: Set<Plugin>

    fun<P: Plugin> get(plugin: Class<P>): P? = plugins.parallelStream()
        .filter(plugin::isInstance).map(plugin::cast).findAny().orElse(null)

    fun add(plugin: Plugin)
    fun remove(plugin: Plugin)

    fun kick(user: U)
    fun sendMessage(msg: List<*>)
}
