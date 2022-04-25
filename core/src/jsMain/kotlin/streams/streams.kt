package de.peekandpoke.kraft.streams

/**
 * Base interface for all streams
 */
interface Stream<T> {
    /**
     * Returns the current value of the stream
     */
    operator fun invoke(): T

    /**
     * Adds a subscription to the stream.
     *
     * On subscribing the subscription is immediately called with the current value.
     *
     * Returns an unsubscribe function. Calling this function unsubscribes from the stream.
     */
    fun subscribeToStream(sub: (T) -> Unit): Unsubscribe
}

/**
 * The [Unsubscribe] function is returned when a subscription is created on a [Stream].
 *
 * Calling this function cancels the subscription.
 */
typealias Unsubscribe = () -> Unit

/**
 * Handler-function for the incoming values of a [Stream].
 */
typealias StreamHandler<T> = (T) -> Unit

/**
 * Subscribes to the stream permanently
 *
 * NOTICE: there is no way to get rid of the subscription anymore. Use with caution.
 */
fun <T> Stream<T>.permanent(): Stream<T> = apply { subscribeToStream { } }

/**
 * Subscribes to the stream permanently
 *
 * NOTICE: there is no way to get rid of the subscription anymore. Use with caution.
 */
fun <T> Stream<T>.permanent(handler: (T) -> Unit): Stream<T> = apply { subscribeToStream(handler) }

/**
 * Define a stream source to which new values can be written.
 */
interface StreamSource<T> : Stream<T> {

    companion object {
        operator fun <T> invoke(initial: T): StreamSource<T> = StreamSourceImpl(initial)
    }

    /**
     * Get the readonly version of this stream.
     */
    val readonly get() = this as Stream<T>

    /**
     * The subscription the stream source has.
     */
    val subscriptions: Set<StreamHandler<T>>

    /**
     * Returns the current value of the stream
     */
    override operator fun invoke(): T

    /**
     * Sends the next value to the stream
     */
    operator fun invoke(next: T)

    /**
     * Calls the [block] with the current value of the stream and sends to return value to the stream.
     *
     * The new value is only sent when it is different from the current value.
     */
    operator fun invoke(block: (T) -> T) {
        val current = this()
        val next = block(current)

        if (current != next) {
            this(next)
        }
    }

    /**
     * Modifies the current value by calling the [block] and sends it the result as the next value.
     *
     * The [block] will have the current value of the stream as the scopes this pointer.
     */
    fun modify(block: T.() -> T): Unit = invoke(block(invoke()))

    /**
     * Removes all subscriptions
     */
    fun removeAllSubscriptions()
}

/**
 * Define a storage backend for a persistent stream
 */
interface StreamStorage<T> {

    fun read(): T?

    fun write(value: T)
}
