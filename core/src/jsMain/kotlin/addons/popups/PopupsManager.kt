package de.peekandpoke.kraft.addons.popups

import de.peekandpoke.kraft.components.onMouseOut
import de.peekandpoke.kraft.components.onMouseOver
import de.peekandpoke.kraft.components.onMouseUp
import de.peekandpoke.kraft.semanticui.css
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.streams.Stream
import de.peekandpoke.kraft.streams.StreamSource
import de.peekandpoke.kraft.utils.Vector2D
import kotlinx.css.*
import kotlinx.html.CommonAttributeGroupFacade
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
            val rect = target.getBoundingClientRect()

            when (positioning) {
                Positioning.BottomLeft -> Vector2D(x = rect.left, y = rect.bottom)
                Positioning.BottomRight -> Vector2D(x = rect.right - contentSize.x, y = rect.bottom)
            }
        }
    }

    fun showContextMenu(event: UIEvent, view: PopupRenderer): Handle {
        event.stopPropagation()
        closeAll()

        val element = event.target as HTMLElement

        return add(element, view) { target, _ ->

            val mouseEvent = event as? MouseEvent

            if (mouseEvent != null) {
                Vector2D(x = mouseEvent.x, y = mouseEvent.y + 7)
            } else {
                val rect = target.getBoundingClientRect()

                Vector2D(x = rect.left, y = rect.bottom + 7)
            }
        }
    }

    internal fun add(element: HTMLElement, view: PopupRenderer, positioning: PopupPositionFn): Handle {
        val content: PopupRenderer = { handle ->
            ui.basic.bottom.visible.popup {
                css {
                    left = 0.px
                    right = LinearDimension.auto
                    padding = Padding(0.px)
                }
                view(handle)
            }
        }

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
