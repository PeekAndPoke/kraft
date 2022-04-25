package de.peekandpoke.kraft.components

import de.peekandpoke.kraft.streams.Stream
import de.peekandpoke.kraft.streams.StreamSource
import de.peekandpoke.kraft.utils.launch
import kotlin.properties.ObservableProperty
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KType

class InlineStateForValue<P>(val component: Component<*>, val initial: P, val type: KType) :
    ObservableProperty<P>(initial) {

    override fun getValue(thisRef: Any?, property: KProperty<*>): P {
        @Suppress("UNCHECKED_CAST")
        return component.inlineProperties.getOrPut(property.getFullName()) {
            initial
        } as P
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: P) {
        val oldValue = getValue(thisRef, property)

        component.inlineProperties[property.getFullName()] = value

        if (oldValue != value) {
            component.triggerRedraw()
        }
    }

    private fun KProperty<*>.getFullName() = "value::${name}::${type}"
}

class InlineStateForStreamSubscription<T>(
    val component: Component<*>, val stream: Stream<T>, val type: KType,
) : ReadOnlyProperty<Any?, T> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {

        component.inlineProperties.getOrPut(property.getFullName()) {
            with(component) {
                var first = true
                stream.subscribe {
                    // The first value will be received right away, but we do not want to trigger a re-draw.
                    if (first) {
                        first = false
                    } else {
                        // redraw the component
                        triggerRedraw()
                    }
                }
            }
        }

        return stream()
    }

    private fun KProperty<*>.getFullName() = "sub::${name}::???"
}

class ObservableComponentProperty<T>(
    private val component: Component<*>,
    private val initialValue: T,
    private val onChange: ((T) -> Unit)? = null
) : ObservableProperty<T>(initialValue) {

    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {

        if (oldValue != newValue) {
            // notify about the change and trigger redraw
            onChange?.invoke(newValue)
            // force the component to redraw
            component.triggerRedraw()
        }
    }

    infix fun setupBy(block: suspend () -> Unit): ObservableComponentProperty<T> = apply {
        launch { block() }
    }

    infix fun onChange(onChange: (T) -> Unit): ObservableComponentProperty<T> =
        ObservableComponentProperty(component = component, initialValue = initialValue, onChange = onChange)
}

class ObservableStreamProperty<T>(
    val component: Component<*>,
    val stream: StreamSource<T>,
) : ObservableProperty<T>(stream()) {

    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {

        if (oldValue != newValue) {
            // notify about the change and trigger redraw
            stream(newValue)
            // force the component to redraw
            component.triggerRedraw()
        }
    }
}
