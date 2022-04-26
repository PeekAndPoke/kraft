package de.peekandpoke.kraft.streams.addons

import de.peekandpoke.kraft.streams.Stream
import de.peekandpoke.kraft.streams.StreamWrapperBase

/**
 * Map the stream to pairs of index to value.
 *
 * The first index is 0.
 */
fun <T> Stream<T>.indexed(): Stream<Pair<Int, T>> {
    return StreamIndexedOperator(this)
}

/**
 * Operator Impl
 */
private class StreamIndexedOperator<T>(
    /** The wrapped stream */
    private val wrapped: Stream<T>,
) : StreamWrapperBase<T, Pair<Int, T>>(
    wrapped = wrapped
) {
    private var idx: Int? = null

    private var latest: Pair<Int, T>? = null

    override fun invoke() = latest ?: Pair(0, wrapped()).also { latest = it }

    override fun handleIncoming(value: T) {

        idx = (idx ?: -1) + 1

        Pair(idx!!, value).also {
            latest = it
            publish(it)
        }
    }
}
