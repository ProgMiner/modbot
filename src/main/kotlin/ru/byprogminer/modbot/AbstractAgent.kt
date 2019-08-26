package ru.byprogminer.modbot

import ru.byprogminer.modbot.message.Attachment
import ru.byprogminer.modbot.utility.RunState
import java.util.*
import java.util.stream.Collectors

abstract class AbstractAgent: Agent {

    abstract val attachmentUploader: AttachmentUploader
    private val chatCache = WeakHashMap<Long, Chat>()
    private val userCache = WeakHashMap<Long, User>()

    private val runState = RunState()

    override val isStarted: Boolean
        get() = runState.isStarted

    override fun start() = runState
        .tryStart(this::onStart)
        .ifNotRun { return }
        .waitForStop(this::onStop)

    override fun stop() = runState.stop()

    override fun sendMessage(chat: Chat, message: List<Any?>, attachments: List<Attachment>) = sendMessage(chat,
        message.parallelStream().map(this::messageMapping).collect(Collectors.joining()), attachments)

    protected abstract fun sendMessage(chat: Chat, message: String, attachments: List<Attachment>)
    protected open fun mention(user: User): String = user.name

    protected open fun messageMapping(element: Any?): String = when (element) {
        is String -> element
        is User -> mention(element)
        else -> Objects.toString(element)
    }

    override fun uploadAttachment() = attachmentUploader

    override fun getChat(id: Long): Chat = chatCache.computeIfAbsent(id, this::doGetChat)
    override fun getUser(id: Long): User = userCache.computeIfAbsent(id, this::doGetUser)

    protected abstract fun doGetChat(id: Long): Chat
    protected abstract fun doGetUser(id: Long): User

    protected open fun onStart() {}
    protected open fun onStop() {}
}
