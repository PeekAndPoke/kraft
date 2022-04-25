package de.peekandpoke.kraft.messages

import de.peekandpoke.kraft.components.Component

/**
 * Message interface
 */
interface Message {
    val sender: Component<*>
    val isStopped: Boolean
}
