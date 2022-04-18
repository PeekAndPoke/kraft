package de.peekandpoke.kraft.components

import de.peekandpoke.kraft.vdom.VDomTagConsumer
import kotlinx.html.CommonAttributeGroupFacade
import kotlinx.html.FlowContent
import kotlinx.html.Tag

typealias RenderFn = FlowContent.() -> Unit
typealias RenderFunc<T> = T.() -> Unit

/**
 * Helps the compiler to identify a code block that is supposed to run on a semantic tag
 */
fun renderFn(block: RenderFn): RenderFn = block

/**
 * Helps the compiler to identify a code block that is supposed to run on a semantic tag
 */
fun <T> renderFn(block: RenderFunc<T>): RenderFunc<T> = block

/**
 * Markup Element key
 */
var CommonAttributeGroupFacade.key: String
    get() = attributes["key"] ?: ""
    set(value) {
        attributes["key"] = value
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


