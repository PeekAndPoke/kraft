package de.peekandpoke.kraft.jsbridges.sourcemappedstacktrace

import de.peekandpoke.kraft.utils.js

fun mapStackTraceCached(stack: String, done: (Array<String>) -> dynamic) = mapStackTrace(
    stack,
    done,
    mapOf(
        "cacheGlobally" to true
    ).js
)
