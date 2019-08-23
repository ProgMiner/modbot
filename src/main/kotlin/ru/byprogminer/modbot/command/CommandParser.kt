package ru.byprogminer.modbot.command

interface CommandParser {

    fun isThisCommand(commandLine: String): Boolean
    fun parseCommand(commandLine: String): Command
}
