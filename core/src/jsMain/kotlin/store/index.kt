package de.peekandpoke.kraft.store

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
