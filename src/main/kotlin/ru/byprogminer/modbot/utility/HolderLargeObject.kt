package ru.byprogminer.modbot.utility

data class HolderLargeObject<out T: Any>(override val value: T): LargeObject {

    override fun asInt(): Int? = value as? Int ?: (value as? Number)?.toInt() ?: (value as? String)?.toIntOrNull()
    override fun asLong(): Long? = value as? Long ?: (value as? Number)?.toLong() ?: (value as? String)?.toLongOrNull()
    override fun asFloat(): Float? = value as? Float ?: (value as? Number)?.toFloat() ?: (value as? String)?.toFloatOrNull()
    override fun asDouble(): Double? = value as? Double ?: (value as? Number)?.toDouble() ?: (value as? String)?.toDoubleOrNull()
    override fun asBoolean(): Boolean? = value as? Boolean ?: (value as? String)?.toBooleanOrNull()
    override fun asString(): String = value as? String ?: value.toString()

    private fun String.toBooleanOrNull() = when (this) {
        "false" -> false
        "true" -> true
        else -> null
    }
}
