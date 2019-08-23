package ru.byprogminer.modbot

import ru.byprogminer.modbot.command.Command
import ru.byprogminer.modbot.command.CommandParser
import java.time.Period
import java.time.ZonedDateTime

interface Plugin {

    val cronPeriod: Period
    val commands: Map<String, CommandParser>

    fun cron(time: ZonedDateTime, chat: Chat<*>)
    fun command(command: Command, chat: Chat<*>)
}
