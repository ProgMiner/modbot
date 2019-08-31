package ru.byprogminer.modbot

import ru.byprogminer.modbot.utility.LargeObject
import java.lang.Exception

abstract class ApiException: Exception {

    constructor(message: String): super(message)
    constructor(message: String, cause: Throwable): super(message, cause)

    abstract val error: LargeObject
}
