package de.peekandpoke.kraft.addons.routing

import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.utils.SimpleAsyncQueue
import kotlinx.browser.window
import kotlinx.coroutines.delay
import org.w3c.dom.events.Event

class BackNavigationTrap(
    private val component: Component<*>,
    private val block: () -> TrapResult,
) {
    companion object {
        private var counter = 0

        fun Component<*>.trapBackNavigation(block: () -> TrapResult) = BackNavigationTrap(
            component = this,
            block = block,
        )
    }

    enum class TrapResult {
        Stop,
        Continue,
    }

    private val queue = SimpleAsyncQueue()

    private var isActive: Boolean = false

    private val data = "back-navigation-trap-${counter++}"

    init {
        component.lifecycle {
            onMount {
                activate()
            }

            onUnmount {
                deactivate()
            }
        }
    }

    private val onPopState: (Event) -> Unit = { _: Event ->
        when (block()) {
            // Can we continue to go back?
            TrapResult.Continue -> deactivate()
            // If not we have to push the state again
            TrapResult.Stop -> pushState()
        }
    }

    fun activate() {
        if (!isActive) {
            isActive = true

            pushState()
            window.addEventListener("popstate", onPopState)
        }
    }

    fun deactivate() {
        if (isActive) {
            isActive = false

            window.removeEventListener("popstate", onPopState)
            goBack()
        }
    }

    private fun pushState() {
        if (window.history.state != data) {
            @Suppress("UnsafeCastFromDynamic")
            window.history.pushState(
                data = data,
                title = undefined.asDynamic(),
                url = window.location.href,
            )
        }
    }

    private fun goBack() {
        queue.add { doGoBack() }
    }

    private suspend fun doGoBack() {
        delay(1)

        val shouldGoBack = window.history.state == data

//        console.log(window.history.state, data, window.location.href, shouldGoBack)

        // Are we still on the same navigation state?
        if (shouldGoBack) {
            console.log("going back")
            window.history.back()

            // Wait for the browser to catch up, as the history.back() is async itself
            delay(100)
//            console.log(window.history.state, data, window.location.href, shouldGoBack)
        }
    }
}
