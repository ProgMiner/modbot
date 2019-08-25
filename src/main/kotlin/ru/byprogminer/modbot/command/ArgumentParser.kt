package ru.byprogminer.modbot.command

interface ArgumentParser<T> {

    // TODO add abstract parser for trimming and i18n

    fun parse(commandLine: String): Pair<String, T>
}
