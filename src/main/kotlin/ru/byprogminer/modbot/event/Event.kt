package ru.byprogminer.modbot.event

import ru.byprogminer.modbot.api.Chat

interface Event {

    val source: Chat
}
