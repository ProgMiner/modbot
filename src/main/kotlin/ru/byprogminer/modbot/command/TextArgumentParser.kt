package ru.byprogminer.modbot.command

class TextArgumentParser: ArgumentParser<String> {

    override fun parse(commandLine: String) = Pair("", commandLine)
}
