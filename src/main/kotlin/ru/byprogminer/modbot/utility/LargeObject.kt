package ru.byprogminer.modbot.utility

/**
 * An object that can be an any object and provides access to his
 * properties and elements (for arrays) by property name and index.
 */
interface LargeObject {

    val value: Any get() = this

    operator fun get(key: String): LargeObject? = null
    operator fun get(index: Int): LargeObject? = null

    fun asInt(): Int? = null
    fun asLong(): Long? = null
    fun asFloat(): Float? = null
    fun asDouble(): Double? = null
    fun asBoolean(): Boolean? = null
    fun asString(): String = toString()
}
