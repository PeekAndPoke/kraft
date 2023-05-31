package de.peekandpoke.kraft.utils

import kotlinx.coroutines.*

private val scopeJob = SupervisorJob()

private val scope = CoroutineScope(scopeJob)

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
