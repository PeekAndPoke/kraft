package de.peekandpoke.kraft.messages

import de.peekandpoke.kraft.components.Component

/**
 * Base impl for [Message].
 */
abstract class MessageBase(override val sender: Component<*>) : Message {
    /** Property indicating if the propagation of the message was stopped */
    override val isStopped get() = _isStopped

    /** Backing field for isStopped */
    private var _isStopped = false

    /** Stops the event from propagation */
    fun stop() {
        _isStopped = true
    }
}

