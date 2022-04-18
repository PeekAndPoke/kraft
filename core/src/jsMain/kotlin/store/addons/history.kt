package de.peekandpoke.kraft.store.addons

import de.peekandpoke.kraft.store.Stream
import de.peekandpoke.kraft.store.StreamWrapperBase

/**
 * Records the latest incoming values up to the given [capacity].
 */
fun <T> Stream<T>.history(capacity: Int): Stream<List<T>> = StreamHistoryOperator(this, capacity)

private class StreamHistoryOperator<T>(
    wrapped: Stream<T>,
    private val capacity: Int,
) : StreamWrapperBase<T, List<T>>(
    wrapped = wrapped
) {
    private val history = mutableListOf<T>()
    private var historyReadOnly = history.toList()

    override fun invoke(): List<T> = historyReadOnly

    override fun handleIncoming(value: T) {

        while (history.size >= capacity) {
            history.removeFirst()
        }

        history.add(value)

        historyReadOnly = history.toList()

        publish(historyReadOnly)
    }
}
