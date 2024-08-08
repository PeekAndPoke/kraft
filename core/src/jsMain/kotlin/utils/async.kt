package de.peekandpoke.kraft.utils

import kotlinx.browser.window
import kotlinx.coroutines.*

private val dispatcher = window.asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun <T : Any?> launch(block: suspend () -> T): Job {
    return scope.launch {
        block()
    }
}

fun <T : Any?> async(block: suspend () -> T): Deferred<T> {
    return scope.async {
        block()
    }
}
