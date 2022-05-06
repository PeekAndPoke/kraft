package de.peekandpoke.kraft.components

import de.peekandpoke.kraft.vdom.VDomTagConsumer
import kotlinx.html.CommonAttributeGroupFacade
import kotlinx.html.Tag

/**
 * Markup Element key
 */
var CommonAttributeGroupFacade.key: String
    get() = attributes["key"] ?: ""
    set(value) {
        attributes["key"] = value
    }

/**
 * Gets the "data-[id]" attribute
 */
fun CommonAttributeGroupFacade.data(id: String): String? = attributes["data-$id"]

/**
 * Sets the "data-[id]" attribute to [value]
 */
fun CommonAttributeGroupFacade.data(id: String, value: String) {
    attributes["data-$id"] = value
}

/**
 * Adds a child component to the current tag
 */
inline fun <P, reified C : Component<P>> Tag.comp(props: P, noinline component: (Ctx<P>) -> C): ComponentRef<C> {
    return (this.consumer as VDomTagConsumer).onComponent(props, component, C::class)
}

/**
 * Adds a parameterless child component to the current tag
 */
inline fun <reified C : Component<Any?>> Tag.comp(noinline component: (NoProps) -> C): ComponentRef<C> {
    return comp(null, component)
}
