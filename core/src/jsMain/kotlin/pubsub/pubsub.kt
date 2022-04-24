package de.peekandpoke.kraft.pubsub

import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.store.Stream
import de.peekandpoke.kraft.store.StreamSource
import de.peekandpoke.ultra.common.TypedKey

private val PubSubKey = TypedKey<PubSub>("pubsub")

class PubSub {
    private val _stream = StreamSource<Message?>(null)

    val stream: Stream<Message?> = _stream

    fun send(message: Message) {
        _stream(message)
    }
}

val Component<*>.pubsub: PubSub
    get() = attributes.getOrPut(PubSubKey) {
        PubSub()
    }

inline fun <reified M : Message> Component<*>.onMessage(noinline handler: (M) -> Unit) {
    pubsub.stream { message ->
        (message as? M)?.let {
            handler(it)
        }
    }
}

fun <M : Message> Component<*>.sendMessage(message: M) {

    // We do not dispatch the message on the component that sent it
    if (message.sender != this) {
        pubsub.send(message)
    } else {
        // but we will trigger a re-draw
        triggerRedraw()
    }

    // Continue with the parent if the message was not stopped
    if (!message.isStopped) {
        parent?.sendMessage(message)
    }
}
