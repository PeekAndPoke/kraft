package de.peekandpoke.kraft.utils

import kotlinx.coroutines.*

private val dispatcher = Dispatchers.Default
private val scope: CoroutineScope = CoroutineScope(dispatcher + SupervisorJob())

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
