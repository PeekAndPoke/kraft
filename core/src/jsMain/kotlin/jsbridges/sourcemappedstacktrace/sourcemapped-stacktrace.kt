@file:JsModule("sourcemapped-stacktrace")
@file:JsNonModule

package de.peekandpoke.kraft.jsbridges.sourcemappedstacktrace

external fun mapStackTrace(stack: String, done: (Array<String>) -> Unit, options: dynamic)
