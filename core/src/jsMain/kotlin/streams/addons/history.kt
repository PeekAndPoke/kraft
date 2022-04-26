package de.peekandpoke.kraft.streams.addons

import de.peekandpoke.kraft.streams.Stream

/**
 * Records the latest incoming values up to the given [capacity].
 */
fun <T> Stream<T>.history(capacity: Int): Stream<List<T>> {
    return fold(emptyList()) { acc, next ->
        acc.plus(next).takeLast(capacity)
    }
}
