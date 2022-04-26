package de.peekandpoke.kraft.streams.addons

import de.peekandpoke.kraft.streams.Stream
import de.peekandpoke.kraft.streams.StreamWrapperBase

/**
 * Folds the incoming values with the [operation] starting with the [initial] value.
 */
fun <T, R> Stream<T>.fold(initial: R, operation: (acc: R, next: T) -> R): Stream<R> =
    StreamFoldOperator(wrapped = this, initial = initial, operation = operation)

/**
 * Operator impl
 */
private class StreamFoldOperator<T, R>(
    wrapped: Stream<T>,
    initial: R,
    private val operation: (acc: R, next: T) -> R,
) : StreamWrapperBase<T, R>(
    wrapped = wrapped
) {
    private var current: R = initial

    override fun invoke(): R = current

    override fun handleIncoming(value: T) {

        current = operation(current, value)

        publish(current)
    }
}
