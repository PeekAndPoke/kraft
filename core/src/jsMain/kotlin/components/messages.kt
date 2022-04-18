package de.peekandpoke.kraft.components

import de.peekandpoke.kraft.store.Stream
import de.peekandpoke.kraft.store.StreamSource

class Messages {
    private val _stream = StreamSource<Message?>(null)
    val stream: Stream<Message?> = _stream

    fun send(message: Message) {
        _stream(message)
    }
}

interface Message {
    val sender: Component<*>
    val isStopped: Boolean
}

abstract class MessageBase(override val sender: Component<*>) :
    Message {
    /** Property indicating if the propagation of the message was stopped */
    override val isStopped get() = _isStopped

    /** Backing field for isStopped */
    private var _isStopped = false

    /** Stops the event from propagation */
    fun stop() {
        _isStopped = true
    }
}

