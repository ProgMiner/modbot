package ru.byprogminer.modbot.storage

open class MemoryStorage(protected val contents: MutableMap<String, Any> = mutableMapOf()): Storage,
    MutableMap<String, Any> by contents
