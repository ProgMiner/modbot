package ru.byprogminer.modbot.utility

data class HolderLargeObject<out T: Any>(override val value: T): LargeObject
