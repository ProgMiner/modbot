package ru.byprogminer.modbot.command

open class IntegerArgumentParser: ArgumentParser<Int> {

    override fun parse(commandLine: String): Pair<String, Int> {
        var lastValue: Int? = null
        var tail = commandLine

        for (cursor in commandLine.indices) {
            try {
                lastValue = commandLine.substring(0, cursor).toInt()
                tail = commandLine.substring(cursor)
            } catch (e: Throwable) {
                break
            }
        }

        requireNotNull(lastValue)
        return Pair(tail, lastValue)
    }
}
