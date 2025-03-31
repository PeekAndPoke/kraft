package de.peekandpoke.kraft.addons.flashmessage

import de.peekandpoke.ultra.common.model.Message
import de.peekandpoke.ultra.common.safeEnumOrNull
import kotlinx.serialization.Serializable
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@Serializable
data class FlashMessage(
    val type: Message.Type,
    val text: String,
    val duration: Duration?,
) {
    companion object {
        fun of(type: String, text: String, duration: Duration? = 5.seconds): FlashMessage {
            val typeEnum = safeEnumOrNull<Message.Type>(type.lowercase().trim()) ?: Message.Type.info

            return FlashMessage(type = typeEnum, text = text, duration = duration)
        }

        fun of(message: Message, duration: Duration = 5.seconds): FlashMessage {
            return FlashMessage(type = message.type, text = message.text, duration = duration)
        }

        fun info(text: String, duration: Duration? = 5.seconds) =
            FlashMessage(type = Message.Type.info, text = text, duration = duration)

        fun warning(text: String, duration: Duration? = 5.seconds) =
            FlashMessage(type = Message.Type.warning, text = text, duration = duration)

        fun error(text: String, duration: Duration? = 5.seconds) =
            FlashMessage(type = Message.Type.error, text = text, duration = duration)
    }
}
