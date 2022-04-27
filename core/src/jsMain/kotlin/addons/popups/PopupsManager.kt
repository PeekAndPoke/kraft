package de.peekandpoke.kraft.addons.popups

import de.peekandpoke.kraft.components.onMouseOut
import de.peekandpoke.kraft.components.onMouseOver
import de.peekandpoke.kraft.components.onMouseUp
import de.peekandpoke.kraft.streams.Stream
import de.peekandpoke.kraft.streams.StreamSource
import de.peekandpoke.kraft.utils.Vector2D
import kotlinx.html.CommonAttributeGroupFacade
import org.w3c.dom.HTMLElement

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
        internal val dialogs: PopupsManager,
    ) {
        internal val onCloseHandlers = mutableListOf<() -> Unit>()

        fun onClose(onClose: () -> Unit) = apply {
            onCloseHandlers.add(onClose)
        }

        fun close() = dialogs.close(this)
    }

    val showHoverPopup = ShowHoverPopup(popups = this)

    private val stack: MutableList<Handle> = mutableListOf()

    private val popupStream: StreamSource<List<Handle>> = StreamSource(emptyList())

    val current: Stream<List<Handle>> = popupStream.readonly

    internal fun add(view: PopupRenderer): Handle {

        return Handle(id = ++handleCounter, view = view, dialogs = this).apply {
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
