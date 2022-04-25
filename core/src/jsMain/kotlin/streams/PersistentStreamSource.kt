package de.peekandpoke.kraft.streams

/**
 * A persistent stream source is able to store values for later use.
 *
 * E.g. values can be stored in the local storage. When the page is then reloaded
 * the latest value can be re-loaded from the storage and the stream will be initialized with it
 */
internal class PersistentStreamSource<T>(
    private val storage: StreamStorage<T>,
    private val wrapped: StreamSource<T>,
) : StreamSource<T> by wrapped {

    init {
        try {
            // Try to load the latest value and publish it on the wrapped stream source
            storage.read()?.let {
                wrapped(it)
            }
        } catch (e: Throwable) {
            println(e)
        }
    }

    /**
     * Incoming values are stored and forwarded to the [wrapped] stream source.
     */
    override fun invoke(next: T) {
        storage.write(next)

        return wrapped(next)
    }
}
