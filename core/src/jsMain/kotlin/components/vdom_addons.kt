package de.peekandpoke.kraft.components

import de.peekandpoke.kraft.store.Stream
import de.peekandpoke.kraft.vdom.VDom
import kotlin.properties.ObservableProperty
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.typeOf

inline fun <reified P> VDom.value(initial: P): ObservableProperty<P> {
    return InlineStateForValue(component!!, initial, typeOf<P>())
}

inline fun <reified P> VDom.subscribingTo(stream: Stream<P>): ReadOnlyProperty<Any?, P> {
    return InlineStateForStreamSubscription(component!!, stream, typeOf<P>())
}
