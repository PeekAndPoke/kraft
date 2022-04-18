package de.peekandpoke.kraft.async

import kotlinx.coroutines.Deferred

@Deprecated(
    message = "Use de.peekandpoke.kraft.utils.launch instead",
    ReplaceWith("de.peekandpoke.kraft.utils.launch"),
)
fun <T : Any?> launch(block: suspend () -> T) = de.peekandpoke.kraft.utils.launch(block)

@Deprecated(
    message = "Use de.peekandpoke.kraft.utils.async instead",
    ReplaceWith("de.peekandpoke.kraft.utils.async"),
)
fun <T : Any?> async(block: suspend () -> T): Deferred<T> = de.peekandpoke.kraft.utils.async(block)
