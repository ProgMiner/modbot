package ru.byprogminer.modbot.command

import ru.byprogminer.modbot.command.argument.Argument
import java.util.regex.Pattern

class DefaultCommandParser(
    private val args: List<Argument<*>>,
    private val namePatterns: Set<Pattern>
): AbstractCommandParser(namePatterns) {

    // I hope this will work
    override fun parseCommand(commandLine: String): Command {
        val commandName = namePatterns.parallelStream()
            .map { it.matcher(commandLine) }
            .filter { it.find() }.findAny()
            .get().group()

        var commandLineTail = commandLine.replaceFirst(commandName, " ")
        val parsedArgs = mutableMapOf<String, Any?>()

        var args = args
        do {
            var exception: Throwable? = null
            var anyParsed = false

            val newArgs = mutableListOf<Argument<*>>()
            for (arg in args) {
                if (anyParsed && newArgs.isNotEmpty()) {
                    newArgs.add(arg)
                    continue
                }

                try {
                    val (newTail, value) = arg.parser.parse(commandLineTail)
                    parsedArgs[arg.name] = value
                    commandLineTail = newTail

                    anyParsed = true
                } catch (e: Throwable) {
                    newArgs.add(arg)

                    if (arg.required) {
                        exception = e
                    }
                }
            }

            if (!anyParsed) {
                if (args.any(Argument<*>::required)) {
                    throw IllegalArgumentException("not all required args passed", exception)
                } else {
                    for (arg in args) {
                        if (!parsedArgs.containsKey(arg.name)) {
                            parsedArgs[arg.name] = arg.default
                        }
                    }
                }
            }

            args = newArgs
        } while (args.isNotEmpty())

        return Command(commandName, parsedArgs)
    }
}
