package ru.byprogminer.modbot.command.argument

data class Argument<T>(
    val name: String,
    val required: Boolean,
    val parser: ArgumentParser<T>,
    val default: T
)
