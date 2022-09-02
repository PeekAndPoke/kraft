package de.peekandpoke.kraft.addons.signaturepad.js

import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.Event

@Suppress("ClassName")
@JsModule("signature_pad")
@JsNonModule
external object signature_pad {
    @JsName("default")
    class SignaturePad(canvas: HTMLCanvasElement) {

        /**
         * Save image as PNG
         */
        fun toDataURL(): String

        /**
         * Save image as the given [mimeType].
         *
         * Possible mime types are 'image/png', 'image/jpeg', 'image/svg+xml'
         */
        fun toDataURL(mimeType: String): String

        /**
         * Save image as the given [mimeType] and the given [quality].
         *
         * Possible mime types are 'image/png', 'image/jpeg', 'image/svg+xml'
         */
        fun toDataURL(mimeType: String, quality: Double): String

        /**
         * Adds an event listener.
         *
         * Events are beginStroke, endStroke, beforeUpdateStroke, afterUpdateStroke
         */
        fun addEventListener(event: String, listener: ((Event) -> Unit))

        /**
         * Removes an event listener.
         *
         * Events are beginStroke, endStroke, beforeUpdateStroke, afterUpdateStroke
         */
        fun removeEventListener(event: String, listener: ((Event) -> Unit))

        /**
         * Clear the contents of the pad
         */
        fun clear()

        /**
         * Returns true if the pad is empty
         */
        fun isEmpty(): Boolean

        /**
         * Unbinds all event listeners
         */
        fun off()

        /**
         * Rebinds all event listeners
         */
        fun on()
    }
}
