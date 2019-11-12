package ru.byprogminer.modbot

abstract class ApiException: Exception {

    constructor(message: String): super(message)
    constructor(message: String, cause: Throwable): super(message, cause)

    abstract val error: Any
    abstract val errorCode: String
    abstract val errorMessage: String
}
