package de.peekandpoke.kraft.examples.forms

import de.peekandpoke.kraft.vdom.preact.PreactVDomEngine
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.browser.document
import org.w3c.dom.HTMLElement

fun main() {
    val mountPoint = document.getElementById("spa") as HTMLElement

    PreactVDomEngine(mountPoint) {
        ui.container {
            ui.dividing.header H1 { +"KRAFT Forms demo" }

            ui.segment {
                ui.header H2 { +"A form with primitive values" }
                FormWithPrimitives()
            }

            ui.segment {
                ui.header H2 { +"A form with nullable primitive values" }
                FormWithNullablePrimitives()
            }

            ui.segment {
                ui.header H2 { +"A form with a text area" }
                FormWithTestArea()
            }

            ui.segment {
                ui.header H2 { +"A form with dates and times" }
                FormWithDates()
            }
        }
    }
}
