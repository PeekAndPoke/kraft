package de.peekandpoke.kraft.addons.flashmessage

import de.peekandpoke.kraft.addons.flashmessage.FlashMessagesManager.Handle
import de.peekandpoke.kraft.components.key
import de.peekandpoke.kraft.components.onClick
import de.peekandpoke.kraft.semanticui.*
import de.peekandpoke.kraft.streams.Stream
import de.peekandpoke.kraft.streams.StreamSource
import de.peekandpoke.kraft.streams.Unsubscribe
import de.peekandpoke.ultra.common.model.Message
import de.peekandpoke.ultra.common.model.Messages
import kotlinx.browser.window
import kotlinx.html.FlowContent
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

typealias FlashMessageRenderer = FlowContent.(Handle) -> Unit

class FlashMessagesManager(
    val defaultDuration: Duration? = 5.seconds,
    val defaultRenderer: FlashMessageRenderer = FlashMessagesManager.defaultRenderer,
) : Stream<List<Handle>> {

    companion object {
        val defaultRenderer: FlashMessageRenderer = { handle ->

            val styleFn: SemanticFn = when (handle.message.type) {
                Message.Type.info -> semantic { green }
                Message.Type.warning -> semantic { warning }
                Message.Type.error -> semantic { error }
            }

            val iconFn: SemanticIconFn = when (handle.message.type) {
                Message.Type.info -> semanticIcon { icon.check_circle }
                Message.Type.warning -> semanticIcon { icon.exclamation_circle }
                Message.Type.error -> semanticIcon { icon.exclamation_circle }
            }

            ui.floating.toastBox.transition.visible {
                ui.styleFn().toast.compact.visible {
                    key = handle.id.toString()
                    onClick { handle.close() }
                    icon.iconFn().render()
                    noui.content { +handle.message.text }
                }
            }


//            when (handle.message.type) {
//                Message.Type.info -> ui.info.toast.compact.visible {
//                    key = handle.id.toString()
//                    onClick { handle.close() }
//                    icon.check_circle()
//                    noui.content { +handle.message.text }
//                }
//
//                Message.Type.warning -> ui.warning.toast.compact.visible {
//                    key = handle.id.toString()
//                    onClick { handle.close() }
//                    icon.exclamation_circle()
//                    noui.content { +handle.message.text }
//                }
//
//                Message.Type.error -> ui.error.toast.compact.visible {
//                    key = handle.id.toString()
//                    onClick { handle.close() }
//                    icon.exclamation_circle()
//                    noui.content { +handle.message.text }
//                }
//            }
        }
    }

    data class Handle(
        val id: Int,
        val message: FlashMessage,
        val view: FlashMessageRenderer,
        internal val flashMessages: FlashMessagesManager,
    ) {
        fun close() = flashMessages.remove(this)
    }

    private var handleCounter = 0

    private val source = StreamSource<List<Handle>>(emptyList())

    override fun invoke(): List<Handle> = source()

    override fun subscribeToStream(sub: (List<Handle>) -> Unit): Unsubscribe = source.subscribeToStream(sub)

    fun info(
        text: String,
        duration: Duration? = defaultDuration,
        renderer: FlashMessageRenderer = defaultRenderer,
    ) {
        append(
            message = FlashMessage.info(text = text, duration = duration),
            renderer = renderer,
        )
    }

    fun warning(
        text: String,
        duration: Duration? = defaultDuration,
        renderer: FlashMessageRenderer = defaultRenderer,
    ) {
        append(
            message = FlashMessage.warning(text = text, duration = duration),
            renderer = renderer,
        )
    }

    fun error(
        text: String,
        duration: Duration? = defaultDuration,
        renderer: FlashMessageRenderer = defaultRenderer,
    ) {
        append(
            message = FlashMessage.error(text = text, duration = duration),
            renderer = renderer,
        )
    }

    fun append(
        type: Message.Type,
        text: String,
        duration: Duration? = defaultDuration,
        renderer: FlashMessageRenderer = defaultRenderer,
    ) = append(
        message = FlashMessage(type = type, text = text, duration = duration),
        renderer = renderer,
    )

    fun append(
        messages: Messages,
        duration: Duration? = defaultDuration,
        renderer: FlashMessageRenderer = defaultRenderer,
    ) {
        messages.getAllMessages().forEach { append(it, duration, renderer) }
    }

    fun append(
        message: Message,
        duration: Duration? = defaultDuration,
        renderer: FlashMessageRenderer = defaultRenderer,
    ) {
        append(
            message = FlashMessage(type = message.type, text = message.text, duration = duration),
            renderer = renderer,
        )
    }

    fun append(
        message: FlashMessage,
        renderer: FlashMessageRenderer = defaultRenderer,
    ) {
        message.duration?.let { duration ->
            // trigger removal of this message after the timeout
            window.setTimeout(
                { remove(message) },
                duration.inWholeMilliseconds.toInt(),
            )
        }

        source.modify {
            plus(message.nextHandle(renderer))
        }
    }

    fun remove(message: FlashMessage) {
        source.modify {
            filterNot { it.message == message }
        }
    }

    fun remove(handle: Handle) {
        source.modify {
            filterNot { it.id == handle.id }
        }
    }

    private fun FlashMessage.nextHandle(renderer: FlashMessageRenderer): Handle {
        val handleId = ++handleCounter

        console.log("new handle: $handleId")

        return Handle(
            id = handleId,
            message = this,
            view = renderer,
            flashMessages = this@FlashMessagesManager,
        )
    }
}
