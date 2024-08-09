package de.peekandpoke.kraft.addons.routing

import de.peekandpoke.kraft.components.Component
import kotlinx.browser.window
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

    private val onPopState = { _: Event ->
        when (block()) {
            // Can we continue to go back?
            TrapResult.Continue -> deactivate()
            // If not we have to push the state again
            TrapResult.Stop -> pushState()
        }
    }

    fun activate() {
        pushState()

        window.addEventListener("popstate", onPopState)
    }

    fun deactivate() {
        window.removeEventListener("popstate", onPopState)

        goBack()
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
        // Are we still on the same navigation state?
        if (window.history.state == data) {
            window.history.back()
        }
    }
}
