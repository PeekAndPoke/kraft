package de.peekandpoke.kraft.addons.popups

import de.peekandpoke.kraft.addons.styling.css
import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.components.Ctx
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.utils.Vector2D
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.css.*
import kotlinx.html.Tag
import kotlinx.html.div
import org.w3c.dom.HTMLElement

@Suppress("FunctionName")
fun Tag.PopupComponent(
    target: HTMLElement,
    positioning: (target: HTMLElement, contentSize: Vector2D) -> Vector2D,
    handle: PopupsManager.Handle,
    content: PopupRenderer,
) = comp(
    PopupComponent.Props(
        target = target,
        positioning = positioning,
        handle = handle,
        content = content,
    )
) {
    PopupComponent(it)
}

class PopupComponent(ctx: Ctx<Props>) : Component<PopupComponent.Props>(ctx) {

    ////  PROPS  //////////////////////////////////////////////////////////////////////////////////////////////////

    data class Props(
        val target: HTMLElement,
        val positioning: (target: HTMLElement, contentSize: Vector2D) -> Vector2D,
        val handle: PopupsManager.Handle,
        val content: PopupRenderer,
    )

    ////  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    private var contentSize: Vector2D by value(Vector2D.zero)

    ////  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    init {
        lifecycle {
            onMount {
                captureContentSize()
            }
        }
    }

    private fun captureContentSize() {
        dom?.firstElementChild?.let {
            val bounds = it.getBoundingClientRect()
            contentSize = Vector2D(bounds.width, bounds.height)
        }
    }

    override fun VDom.render() {

        captureContentSize()

        // Just a container
        div {
            css {
                display = Display.block
                position = Position.fixed
                zIndex = 2000

                val pos = props.positioning(props.target, contentSize)

                left = pos.x.px
                top = pos.y.px

                if (contentSize == Vector2D.zero) {
                    visibility = Visibility.hidden
                }
            }

            props.content(this, props.handle)
        }
    }
}

