package ru.byprogminer.modbot.command.argument

interface ArgumentParser<T> {

    fun parse(commandLine: String): Pair<String, T>
}
