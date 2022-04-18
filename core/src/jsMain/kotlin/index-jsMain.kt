package de.peekandpoke.kraft

import de.peekandpoke.ultra.common.datetime.kotlinx.initializeJsJodaTimezones

/**
 * Initializes all external libraries and the returns [Kraft].
 */
fun Kraft.Companion.initialize(): Kraft {
    initializeJsJodaTimezones()

    return Kraft()
}
