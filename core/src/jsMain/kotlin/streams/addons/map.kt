package de.peekandpoke.kraft.streams.addons

import de.peekandpoke.kraft.streams.Stream
import de.peekandpoke.kraft.streams.StreamMapper

/**
 * Maps incoming values from type [IN] to type [OUT].
 */
fun <IN, OUT> Stream<IN>.map(mapper: (IN) -> OUT): Stream<OUT> =
    StreamMapOperator(this, mapper)

/**
 * Operator impl for [map]
 */
private class StreamMapOperator<IN, OUT>(
    wrapped: Stream<IN>,
    mapper: (IN) -> OUT
) : StreamMapper<IN, OUT>(wrapped, mapper)

