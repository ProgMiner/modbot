package ru.byprogminer.modbot.utility

/**
 * An object that can be an any object and provides access to his
 * properties and elements (for arrays) by property name and index.
 */
interface LargeObject {

    val value: Any get() = this

    operator fun get(key: String): LargeObject? = null
    operator fun get(index: Int): LargeObject? = null

    fun asInt(): Int? = value as? Int
    fun asLong(): Long? = value as? Long
    fun asFloat(): Float? = value as? Float
    fun asDouble(): Double? = value as? Double
    fun asBoolean(): Boolean? = value as? Boolean
    fun asString(): String? = value as? String
}
