package ru.byprogminer.modbot.command

open class LineArgumentParser: ArgumentParser<String> {

    override fun parse(commandLine: String) = commandLine.indexOf('\n').let { lineBreak ->
        Pair(commandLine.substring(lineBreak + 1), commandLine.substring(0, lineBreak)) }
}
