package de.peekandpoke.kraft.addons.marked

// Type-Definitions for marked-js

@Suppress("ClassName")
@JsModule("marked")
@JsNonModule
external object marked {
    /**
     * Parses the [content] as markdown and returns html as string
     */
    fun parse(content: String): String
}
