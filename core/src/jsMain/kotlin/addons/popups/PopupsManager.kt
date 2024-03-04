package de.peekandpoke.kraft.addons.popups

import de.peekandpoke.kraft.components.onMouseOut
import de.peekandpoke.kraft.components.onMouseOver
import de.peekandpoke.kraft.components.onMouseUp
import de.peekandpoke.kraft.streams.Stream
import de.peekandpoke.kraft.streams.StreamSource
import de.peekandpoke.kraft.utils.Rectangle
import de.peekandpoke.kraft.utils.Vector2D
import kotlinx.browser.document
import kotlinx.html.CommonAttributeGroupFacade
import org.w3c.dom.DOMRect
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.events.UIEvent

typealias PopupPositionFn = (target: HTMLElement, contentSize: Vector2D) -> Vector2D

class PopupsManager {
    private var handleCounter = 0

    class ShowHoverPopup(private val popups: PopupsManager) {

        fun show(
            tag: CommonAttributeGroupFacade,
            positioning: (target: HTMLElement, contentSize: Vector2D) -> Vector2D,
            view: PopupRenderer
        ) {
            with(tag) {
                var handle: Handle? = null

                val close = {
                    handle?.let { h ->
                        popups.close(h)
                        handle = null
                    }
                }

                onMouseOver { event ->
                    (event.target as? HTMLElement)?.let { target ->
                        if (handle != null) {
                            return@let
                        }

                        popups.add { h ->
                            handle = h

                            PopupComponent(target = target, positioning = positioning, handle = h, content = view)
                        }
                    }
                }
                onMouseOut { close() }
                // In case there is a link, we also close the popup when the link is clicked.
                onMouseUp { close() }
            }
        }
    }

    class Handle internal constructor(
        val id: Int,
        val view: PopupRenderer,
        internal val manager: PopupsManager,
    ) {
        internal val onCloseHandlers = mutableListOf<() -> Unit>()

        fun onClose(onClose: () -> Unit) = apply {
            onCloseHandlers.add(onClose)
        }

        fun close() = manager.close(this)
    }

    enum class Positioning {
        BottomLeft,
        BottomRight,
    }

    val showHoverPopup = ShowHoverPopup(popups = this)

    private val stack: MutableList<Handle> = mutableListOf()

    private val popupStream: StreamSource<List<Handle>> = StreamSource(emptyList())

    val current: Stream<List<Handle>> = popupStream.readonly

    fun showContentMenu(
        event: UIEvent,
        positioning: Positioning = Positioning.BottomLeft,
        view: PopupRenderer
    ): Handle {
        event.stopPropagation()
        closeAll()

        val element = event.target as HTMLElement

        return add(element, view) { target, contentSize ->
            val coords = getPageCoords(target)

            when (positioning) {
                Positioning.BottomLeft -> coords.bottomLeft
                Positioning.BottomRight -> coords.bottomRight - Vector2D(contentSize.x, 0.0)
            }
        }
    }

    fun showContextMenu(event: UIEvent, view: PopupRenderer): Handle {
        event.stopPropagation()
        closeAll()

        val element = event.target as HTMLElement
        val moveDown = Vector2D(0.0, 7.0)

        return add(element, view) { target, _ ->

            val mouseEvent: MouseEvent? = event as? MouseEvent

            if (mouseEvent != null) {
                Vector2D(x = mouseEvent.pageX, y = mouseEvent.pageY + 7)
            } else {
                getPageCoords(target).bottomLeft.plus(moveDown)
            }
        }
    }

    private fun getPageCoords(element: HTMLElement): Rectangle {
        val body = document.body?.getBoundingClientRect() ?: DOMRect()
        val rect = element.getBoundingClientRect()

        return Rectangle(
            x1 = (rect.left - body.left),
            y1 = (rect.top - body.top),
            width = rect.width,
            height = rect.height,
        )
    }

    internal fun add(element: HTMLElement, content: PopupRenderer, positioning: PopupPositionFn): Handle {
        return add { handle ->
            PopupComponent(
                target = element,
                positioning = positioning,
                handle = handle,
                content = content,
            )
        }
    }

    internal fun add(view: PopupRenderer): Handle {

        return Handle(id = ++handleCounter, view = view, manager = this).apply {
            stack.add(this)
            notify()
        }
    }

    internal fun close(handle: Handle) {
        // call onClose handlers
        stack.filter { it.id == handle.id }
            .forEach { it.onCloseHandlers.forEach { handler -> handler() } }

        // Remove from stack
        stack.removeAll { it.id == handle.id }

        notify()
    }

    fun closeAll() {
        stack.clear()
        notify()
    }

    private fun notify() {
        popupStream(stack)
    }
}
