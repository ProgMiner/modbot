package ru.byprogminer.modbot.storage

interface Storage: MutableMap<String, Any> {

    @Suppress("UNCHECKED_CAST")
    fun<T: Any> get(key: String, clazz: Class<T>): T? = this[key] as T?
}
