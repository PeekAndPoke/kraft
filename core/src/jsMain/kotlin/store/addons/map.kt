package de.peekandpoke.kraft.store.addons

import de.peekandpoke.kraft.store.AsyncMappingStreamWrapper
import de.peekandpoke.kraft.store.MappingStreamWrapper
import de.peekandpoke.kraft.store.Stream

/**
 * Maps incoming values from type [IN] to type [OUT].
 */
fun <IN, OUT> Stream<IN>.map(mapper: (IN) -> OUT): Stream<OUT> =
    StreamMapOperator(this, mapper)

/**
 * Maps incoming values asynchronously from type [IN] to type [OUT].
 *
 * Notice that the result of the [mapper] can be null.
 * In this case a 'null' will be published.
 */
fun <IN, OUT> Stream<IN>.mapAsync(mapper: suspend (IN) -> OUT?): Stream<OUT?> =
    StreamMapAsyncOperator(this, mapper)

/**
 * Operator impl for [map]
 */
private class StreamMapOperator<IN, OUT>(
    wrapped: Stream<IN>,
    mapper: (IN) -> OUT
) : MappingStreamWrapper<IN, OUT>(wrapped, mapper)

/**
 * Operator impl for [mapAsync]
 */
private class StreamMapAsyncOperator<IN, OUT>(
    wrapped: Stream<IN>,
    mapper: suspend (IN) -> OUT?
) : AsyncMappingStreamWrapper<IN, OUT>(wrapped, mapper)
