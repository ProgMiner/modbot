package ru.byprogminer.modbot.command

open class NumericArgumentParser: ArgumentParser<Double> {

    override fun parse(commandLine: String): Pair<String, Double> {
        var lastValue: Double? = null
        var tail = commandLine

        for (cursor in commandLine.indices) {
            try {
                lastValue = commandLine.substring(0, cursor).toDouble()
                tail = commandLine.substring(cursor)
            } catch (e: Throwable) {
                break
            }
        }

        requireNotNull(lastValue)
        return Pair(tail, lastValue)
    }
}
