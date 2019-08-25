package ru.byprogminer.modbot.event

import ru.byprogminer.modbot.User

interface UserEvent: Event {

    val user: User
}
