package de.peekandpoke.kraft.components

interface ComponentRef<C : Component<*>> {

    fun getComponent(): C?

    operator fun invoke(): C? = getComponent()

    operator fun invoke(block: C.() -> Unit) {
        invoke()?.block()
    }

    fun trackRef(tracker: Tracker<C>): ComponentRef<C> = apply {
        tracker.track(this)
    }

    class Tracker<C : Component<*>>(ref: ComponentRef<C>? = null) : ComponentRef<C> {

        private var _ref: ComponentRef<C>? = ref

        override fun getComponent(): C? = _ref?.getComponent()

        fun track(ref: ComponentRef<C>) {
            _ref = ref
        }
    }

    class Null<C : Component<*>> : ComponentRef<C> {
        override fun getComponent(): C? {
            return null
        }
    }
}
