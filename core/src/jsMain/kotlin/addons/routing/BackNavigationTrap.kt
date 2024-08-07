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
//        console.log(evt)

        val result = block()

        if (result == TrapResult.Stop) {
            window.history.go(1)
        }
    }

    fun activate() {
//        console.log("activating $data")

        @Suppress("UnsafeCastFromDynamic")
        window.history.pushState(data, undefined.asDynamic(), window.location.href)

        window.addEventListener("popstate", onPopState)
    }

    fun deactivate() {
//        console.log("deactivating $data")

        window.removeEventListener("popstate", onPopState)

        // Are we still on the same navigation state?
        if (window.history.state == data) {
            // go back
            window.history.back()
        }
    }
}
