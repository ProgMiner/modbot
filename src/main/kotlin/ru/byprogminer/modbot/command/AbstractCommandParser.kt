package ru.byprogminer.modbot.command

import java.util.regex.Pattern

abstract class AbstractCommandParser(private val namePatterns: Set<Pattern>): CommandParser {

    override fun isThisCommand(commandLine: String): Boolean =
        namePatterns.parallelStream().anyMatch { it.matcher(commandLine).find() }
}
