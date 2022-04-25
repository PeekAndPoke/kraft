package de.peekandpoke.kraft.streams.addons

import de.peekandpoke.kraft.streams.Stream
import de.peekandpoke.kraft.streams.StreamWrapperBase

/**
 * Filter the incoming values and publish only the ones that match the [predicate].
 */
fun <T> Stream<T>.filter(predicate: (T) -> Boolean): Stream<T?> =
    StreamFilterOperator(wrapped = this, predicate = predicate)

/**
 * Filter the incoming values and publish only the ones that match the [predicate].
 */
fun <T> Stream<T>.filter(initial: T, predicate: (T) -> Boolean): Stream<T> =
    StreamFilterWithInitialValueOperator(wrapped = this, initial = initial, predicate = predicate)

/**
 * Filter out all null values.
 */
@Suppress("UNCHECKED_CAST")
fun <T> Stream<T?>.filterNotNull(initial: T): Stream<T> =
    filter(initial = initial, predicate = { it != null }) as Stream<T>

private class StreamFilterOperator<T>(
    wrapped: Stream<T>,
    private val predicate: (T) -> Boolean
) : StreamWrapperBase<T, T?>(wrapped = wrapped) {

    private var latest: T? = null

    override fun invoke(): T? = latest

    override fun handleIncoming(value: T) {
        if (predicate(value)) {
            latest = value
            publish(value)
        }
    }
}

private class StreamFilterWithInitialValueOperator<T>(
    wrapped: Stream<T>,
    initial: T,
    private val predicate: (T) -> Boolean
) : StreamWrapperBase<T, T>(wrapped = wrapped) {

    private var latest: T = initial

    override fun invoke(): T = latest

    override fun handleIncoming(value: T) {
        if (predicate(value)) {
            latest = value
            publish(value)
        }
    }
}
