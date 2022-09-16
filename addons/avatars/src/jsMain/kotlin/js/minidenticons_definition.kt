package de.peekandpoke.kraft.addons.avatars.js

@JsModule("minidenticons")
@JsNonModule
external object JsMinIdenticons {
    fun identicon(
        name: String,
        saturation: Number? = definedExternally,
        lightness: Number? = definedExternally,
    ): String
}

