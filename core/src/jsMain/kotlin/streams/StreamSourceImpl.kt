package de.peekandpoke.kraft.streams

open class StreamSourceImpl<T>(initialValue: T) : StreamSource<T> {

    /**
     * The current value of the stream
     */
    protected var current: T = initialValue

    /**
     * All subscriptions to the stream
     */
    override val subscriptions: MutableSet<(T) -> Unit> = mutableSetOf()

    /**
     * @see StreamSource.invoke
     */
    override operator fun invoke(): T = current

    /**
     * @see StreamSource.invoke
     */
    override operator fun invoke(next: T) {
        current = next
        subscriptions.forEach { it(current) }
    }

    /**
     * @see StreamSource.subscribeToStream
     */
    override fun subscribeToStream(sub: StreamHandler<T>): Unsubscribe {
        this.subscriptions.add(sub)

        onSub()

        sub(current)

        return {
            subscriptions.remove(sub)
            onUnSub()
        }
    }

    /**
     * @see StreamSource.removeAllSubscriptions
     */
    override fun removeAllSubscriptions() {
        this.subscriptions.clear()
    }

    /**
     * Hook called when a subscription was added
     */
    protected open fun onSub() {}

    /**
     * Hook called when a subscription was unsubscribed
     */
    protected open fun onUnSub() {}
}
