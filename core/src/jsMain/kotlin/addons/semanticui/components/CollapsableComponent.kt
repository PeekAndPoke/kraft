package de.peekandpoke.kraft.addons.semanticui.components

import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.components.Ctx
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.onAnimationEnd
import de.peekandpoke.kraft.semanticui.RenderFn
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.*
import org.w3c.dom.HTMLElement

@Suppress("FunctionName")
fun Tag.Collapsable(builder: CollapsableComponent.Builder.() -> Unit) =
    comp(CollapsableComponent.Builder().apply(builder).build()) { CollapsableComponent(it) }

class CollapsableComponent(ctx: Ctx<Props>) : Component<CollapsableComponent.Props>(ctx) {

    data class Props(
        val header: (DIV.(HeaderCtx) -> Unit)?,
        val content: RenderFn,
        val collapsed: Boolean
    )

    class Builder(
        var header: (DIV.(HeaderCtx) -> Unit)? = null,
        var content: RenderFn = {},
        var collapsed: Boolean = true
    ) {
        internal fun build() = Props(header, content, collapsed)

        fun header(block: FlowContent.(HeaderCtx) -> Unit) {
            header = block
        }

        fun content(block: RenderFn) {
            content = block
        }
    }

    data class HeaderCtx(
        val collapsed: Boolean,
        val toggle: () -> Unit
    )

    ////  STATE  ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private var collapsed by value(props.collapsed)
    private var transitioning by value(false)
    private var scrollHeight by value<Int?>(null)

    ////  IMPL  ////////////////////////////////////////////////////////////////////////////////////////////////////////

    override fun shouldRedraw(nextProps: Props): Boolean {

        scrollHeight = (dom?.lastChild as? HTMLElement)?.scrollHeight

        return super.shouldRedraw(nextProps)
    }

    fun toggle() {
        collapsed = !collapsed
        transitioning = true
    }

    override fun VDom.render() {

        div {
            div {
                props.header?.let { header ->
                    header(this, HeaderCtx(collapsed, ::toggle))
                }
            }

            div {
                onAnimationEnd {
                    transitioning = false
                }

                if (!collapsed) {
                    if (scrollHeight != null) {
                        style =
                            "max-height: ${scrollHeight}px; transition: max-height 0.2s ease-in; " + if (transitioning) {
                                "overflow: hidden;"
                            } else {
                                ""
                            }
                    }
                } else {
                    style = "max-height: 0; transition: max-height 0.2s ease-out; overflow: hidden;"
                }

                props.content(this)
            }
        }
    }
}
