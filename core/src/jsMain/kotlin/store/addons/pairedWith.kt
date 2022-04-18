package de.peekandpoke.kraft.store.addons

import de.peekandpoke.kraft.store.Stream
import de.peekandpoke.kraft.store.StreamCombinator

/**
 * Pairs the stream with the [other] stream.
 *
 * This stream publishes a [Pair] of the current value of [this] and [other],
 * whenever [this] stream or the [other] stream published a new value.
 */
fun <IN1, IN2> Stream<IN1>.pairedWith(other: Stream<IN2>): Stream<Pair<IN1, IN2>> {
    return StreamCombinator(
        first = this,
        second = other,
        combine = { first, second -> Pair(first, second) }
    )
}
