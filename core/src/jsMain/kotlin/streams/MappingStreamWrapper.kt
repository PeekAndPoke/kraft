package de.peekandpoke.kraft.streams

import de.peekandpoke.kraft.utils.launch

/**
 * Base class for stream wrappers that map the value type from [WRAPPED] to [RESULT]
 *
 * @param WRAPPED The value type of the wrapped stream
 * @param RESULT  The value type of the resulting stream
 */
abstract class MappingStreamWrapper<WRAPPED, RESULT>(
    /** The wrapped stream */
    private val wrapped: Stream<WRAPPED>,
    /** Maps the value of the [wrapped] stream to the result ([WRAPPED] to [RESULT]) */
    private val mapper: (WRAPPED) -> RESULT
) : StreamWrapperBase<WRAPPED, RESULT>(
    wrapped = wrapped
) {
    override fun invoke(): RESULT = mapper(wrapped())

    override fun handleIncoming(value: WRAPPED): Unit = publish(mapper(value))
}

/**
 * Base class for stream wrappers that map the value type from [WRAPPED] to [RESULT]
 *
 * @param WRAPPED The value type of the wrapped stream
 * @param RESULT  The value type of the resulting stream
 */
abstract class AsyncMappingStreamWrapper<WRAPPED, RESULT>(
    /** The wrapped stream */
    private val wrapped: Stream<WRAPPED>,
    /** Maps the value of the [wrapped] stream to the result ([WRAPPED] to [RESULT]) */
    private val mapper: suspend (WRAPPED) -> RESULT?
) : StreamWrapperBase<WRAPPED, RESULT?>(
    wrapped = wrapped
) {
    private var latest: RESULT? = null

    override fun invoke(): RESULT? = latest

    override fun handleIncoming(value: WRAPPED) {
        launch {
            val mapped = mapper(value)
            latest = mapped
            publish(mapped)
        }
    }
}
