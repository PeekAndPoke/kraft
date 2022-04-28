package de.peekandpoke.kraft.examples.forms

import de.peekandpoke.kraft.vdom.preact.PreactVDomEngine
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.browser.document
import org.w3c.dom.HTMLElement

fun main() {
    val mountPoint = document.getElementById("spa") as HTMLElement

    PreactVDomEngine(mountPoint) {
        ui.container {
            ui.header H1 { +"KRAFT Forms" }

            FirstForm()
        }
    }
}
