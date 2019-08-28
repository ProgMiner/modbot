package ru.byprogminer.modbot.event

import ru.byprogminer.modbot.api.User

interface UserEvent: Event {

    val user: User
}
