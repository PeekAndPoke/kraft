package de.peekandpoke.kraft.utils

import kotlinx.browser.window
import org.w3c.dom.Window

/**
 * Helper function for a nicer use of [Window.setTimeout]
 *
 * @return The timer id
 */
fun setTimeout(timeMs: Int, block: () -> Unit): Int {
    return window.setTimeout(block, timeMs)
}

/**
 * Helper class implementing a debouncing timer.
 *
 * The block() passed to [schedule] will only be executed after [delayMs]
 * if no other call to [schedule] occurred in the meanwhile.
 */
class DebouncingTimer(private val delayMs: Int, private val delayFirstMs: Int = delayMs) {

    private var counter = 0
    private var timerId: Int? = null

    operator fun invoke(block: () -> Unit) {
        if (counter++ == 0) {
            schedule(delayFirstMs, block)
        } else {
            schedule(delayMs, block)
        }
    }

    fun schedule(delay: Int, block: () -> Unit) {
        timerId?.let { window.clearTimeout(it) }

        timerId = setTimeout(delay) {
            timerId = null
            block()
        }
    }
}
