package ru.byprogminer.modbot.command

data class Command(
    val name: String,
    val args: Map<String, *>
)
