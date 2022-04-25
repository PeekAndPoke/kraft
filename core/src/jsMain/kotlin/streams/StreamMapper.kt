package de.peekandpoke.kraft.streams

/**
 * Base class for stream wrappers that map the value type from [WRAPPED] to [RESULT]
 *
 * @param WRAPPED The value type of the wrapped stream
 * @param RESULT  The value type of the resulting stream
 */
abstract class StreamMapper<WRAPPED, RESULT>(
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

