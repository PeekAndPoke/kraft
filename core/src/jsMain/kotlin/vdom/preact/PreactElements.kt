package de.peekandpoke.kraft.vdom.preact

import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.components.ComponentRef
import de.peekandpoke.kraft.components.Ctx
import de.peekandpoke.kraft.utils.js
import de.peekandpoke.kraft.utils.jsArray
import de.peekandpoke.kraft.utils.jsObject
import de.peekandpoke.kraft.vdom.VDomElement
import kotlinx.html.Tag
import kotlinx.html.Unsafe
import org.w3c.dom.events.Event
import preact.h
import kotlin.reflect.KClass

internal interface PreactElements {

    class PreactComponentRef<C : Component<*>> : ComponentRef<C> {

        internal var ll: PreactLLC? = null

        override fun getComponent(): C? {
            @Suppress("UNCHECKED_CAST")
            return ll?.getComponent() as? C?
        }
    }

    data class RootElement(
        val children: MutableList<VDomElement> = mutableListOf(),
    ) : VDomElement {
        override fun appendChild(child: VDomElement) {
            children.add(child)
        }

        override fun render(): dynamic {
            val result = jsArray()

            for (child in children) {
                result.push(child.render())
            }

            return result
        }
    }

    data class ComponentElement<P, C : Component<P>>(
        private val componentCtx: Ctx<P>,
        private val creatorFn: (Ctx<P>) -> C,
        private val cls: KClass<C>,
    ) : VDomElement {

        val ref = PreactComponentRef<C>()
        val ctor = PreactLLC.getLowLevelComponentCtor(cls)

        override fun render(): dynamic {

            return h(
                ctor,
                jsObject<PreactLLCProps> {
                    this.__ctx = componentCtx
                    @Suppress("UNCHECKED_CAST")
                    this.__ctor = creatorFn as (Ctx<*>) -> Component<*>
                    this.__ref = ref
                },
            )
        }
    }

    data class ContentElement(
        val content: CharSequence,
    ) : VDomElement {
        override fun render(): dynamic {
            return content
        }
    }

    data class TagElement(
        val tag: Tag,
        val children: MutableList<VDomElement> = mutableListOf(),
        val events: MutableMap<String, (Event) -> Any?> = mutableMapOf(),
    ) : VDomElement {

        override fun appendChild(child: VDomElement) {
            children.add(child)
        }

        override fun addEvent(name: String, callback: (Event) -> Any?) {
            events[name] = callback
        }

        override fun render(): dynamic {
            // Convert attributes to plain js object
            val attrs = tag.attributes.js

            // Create a low level array for the render results of the children
            val childArr = jsArray()

            // map the children to a native array
            for (child in children) {
                @Suppress("UnsafeCastFromDynamic")
                childArr.push(child.render())
            }

            // Merge the events into the attributes
            events.forEach { (k, v) -> attrs[k] = v }

            // Render with mithril
            return h(tag.tagName, attrs, childArr)
        }
    }

    class UnsafeContentElement : VDomElement, Unsafe {

        private var _content = ""

        val content get() = _content

        override fun String.unaryPlus() {
            _content += this
        }

        override fun render(): dynamic {
            return h(
                "span",
                jsObject {
                    dangerouslySetInnerHTML = jsObject {
                        __html = content
                    }
                }
            )
        }
    }
}
