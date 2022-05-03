package de.peekandpoke.kraft.examples.forms

import de.peekandpoke.kraft.vdom.preact.PreactVDomEngine
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.browser.document
import org.w3c.dom.HTMLElement

fun main() {
    val mountPoint = document.getElementById("spa") as HTMLElement

    PreactVDomEngine(mountPoint) {
        ui.basic.padded.segment {
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
                ui.header H2 { +"A form with checkboxes" }
                FormWithCheckboxes()
            }

            ui.segment {
                ui.header H2 { +"A form with a text area" }
                FormWithTestArea()
            }

            ui.segment {
                ui.header H2 { +"A form with passwords" }
                FormWithPasswords()
            }

            ui.segment {
                ui.header H2 { +"A form with Dates " }
                FormWithDates()
            }

            ui.segment {
                ui.header H2 { +"A form with nullable Dates" }
                FormWithNullableDates()
            }

            ui.segment {
                ui.header H2 { +"A form with DateTimes" }
                FormWithDateTimes()
            }

            ui.segment {
                ui.header H2 { +"A form with nullable DateTimes" }
                FormWithNullableDateTimes()
            }

            ui.segment {
                ui.header H2 { +"A form with Times" }
                FormWithTimes()
            }
        }
    }
}
