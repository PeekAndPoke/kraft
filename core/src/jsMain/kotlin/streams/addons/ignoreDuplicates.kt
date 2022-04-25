package de.peekandpoke.kraft.streams.addons

import de.peekandpoke.kraft.streams.Stream
import de.peekandpoke.kraft.streams.StreamWrapper

/**
 * Ignores duplicate values and thus only publishes when the values has changed.
 *
 * By default, the comparison between the previous and current value is not strict ==.
 * You can set [strict] to true to use strict === comparison
 */
fun <T> Stream<T>.ignoreDuplicates(strict: Boolean = false): Stream<T> =
    StreamIgnoreDuplicatesOperator(wrapped = this, strict = strict)

private class StreamIgnoreDuplicatesOperator<T>(
    wrapped: Stream<T>,
    private val strict: Boolean
) : StreamWrapper<T>(wrapped = wrapped) {

    private var latest: T = wrapped()

    override fun invoke(): T = latest

    override fun handleIncoming(value: T) {

        val accept = when (strict) {
            true -> value !== latest
            else -> value != latest
        }

        latest = value

        if (accept) {
            super.handleIncoming(value)
        }
    }
}
