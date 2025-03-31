package de.peekandpoke.kraft

import de.peekandpoke.kraft.addons.flashmessage.FlashMessagesManager
import de.peekandpoke.kraft.addons.flashmessage.FlashMessagesStage
import de.peekandpoke.kraft.addons.modal.ModalsManager
import de.peekandpoke.kraft.addons.modal.ModalsStage
import de.peekandpoke.kraft.addons.popups.PopupsManager
import de.peekandpoke.kraft.addons.popups.PopupsStage
import de.peekandpoke.ultra.common.datetime.kotlinx.initializeJsJodaTimezones
import kotlinx.html.FlowContent

@DslMarker
annotation class KraftDsl

class Kraft internal constructor() {
    companion object {
        /**
         * Initializes all external libraries and the returns [Kraft].
         */
        fun initialize(): Kraft {
            initializeJsJodaTimezones()

            return Kraft()
        }
    }

    val modals = ModalsManager()
    val popups = PopupsManager()
    val flash = FlashMessagesManager()

    fun mount(tag: FlowContent, block: FlowContent.() -> Unit) {
        with(tag) {
            ModalsStage(modals)
            PopupsStage(popups)
            FlashMessagesStage(flash)

            block()
        }
    }
}
