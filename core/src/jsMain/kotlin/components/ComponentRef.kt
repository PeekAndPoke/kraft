package de.peekandpoke.kraft.components

import de.peekandpoke.kraft.utils.launch
import kotlinx.coroutines.delay

interface ComponentRef<C : Component<*>> {

    fun getComponent(): C?

    operator fun invoke(): C? = getComponent()

    operator fun invoke(block: (C) -> Unit) {
        getComponent()?.let {
            block(it)
        }
    }

    fun track(tracker: Tracker<C>): ComponentRef<C> = apply {
        tracker.track(this)
    }

    class Tracker<C : Component<*>> : ComponentRef<C> {

        private var previousRef: ComponentRef<C>? = null
        private var currentRef: ComponentRef<C>? = null

        private var alreadyTracked: Boolean = false

        override fun getComponent(): C? {
            return currentRef?.getComponent() ?: previousRef?.getComponent()
        }

        internal fun track(ref: ComponentRef<C>) {
            previousRef = currentRef
            currentRef = ref

            if (!alreadyTracked) {
                waitForComponent()
            }
        }

        private fun waitForComponent() {
            val comp = getComponent()

            if (comp == null) {
                launch {
                    delay(10)
                    waitForComponent()
                }
            } else {
                alreadyTracked = true
                comp.parent?.triggerRedraw()
            }
        }
    }

    class Null<C : Component<*>> : ComponentRef<C> {
        override fun getComponent(): C? {
            return null
        }
    }
}
