package de.peekandpoke.kraft.jsbridges.markedjs

// Type-Definitions for markedjs

/**
 * Parses the [content] as markdown and returns html as string
 */
@JsModule("marked")
@JsNonModule
external fun marked(content: String): String
