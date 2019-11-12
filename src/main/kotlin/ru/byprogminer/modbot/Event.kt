package ru.byprogminer.modbot

import ru.byprogminer.modbot.api.Chat
import ru.byprogminer.modbot.api.Message
import ru.byprogminer.modbot.api.User

sealed class Event {

    sealed class UserEvent: Event() {

        sealed class MessageEvent: UserEvent() {

            abstract class CustomMessageEvent(): MessageEvent()

            data class NewMessageEvent(
                override val source: Chat,
                override val user: User,
                override val message: Message
            ): MessageEvent()

            data class MessageEditedEvent(
                override val source: Chat,
                override val user: User,
                override val message: Message
            ): MessageEvent()

            data class MessageRemovedEvent(
                override val source: Chat,
                override val user: User,
                override val message: Message
            ): MessageEvent()

            abstract val message: Message
        }

        abstract class CustomUserEvent(): UserEvent()

        data class UserJoinedEvent(override val source: Chat, override val user: User): UserEvent()
        data class UserLeftEvent(override val source: Chat, override val user: User): UserEvent()

        abstract val user: User
    }

    abstract class CustomEvent(): Event()

    data class NewChatEvent(override val source: Chat): Event()

    abstract val source: Chat
}
