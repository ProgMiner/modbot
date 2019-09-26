package ru.byprogminer.modbot.command

open class TextArgumentParser: ArgumentParser<String> {

    override fun parse(commandLine: String) = Pair("", commandLine)
}
