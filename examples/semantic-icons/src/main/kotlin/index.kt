package de.peekandpoke.kraft.examples.semantic.forms

import de.peekandpoke.kraft.vdom.preact.PreactVDomEngine
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.browser.document
import org.w3c.dom.HTMLElement

fun main() {
    val mountPoint = document.getElementById("spa") as HTMLElement

    PreactVDomEngine(mountPoint) {
        ui.basic.padded.segment {
            ui.dividing.header H1 { +"KRAFT SemanticUi icons demo" }

            ui.dividing.header H2 { +"Examples" }
            IconExamples()

            ui.dividing.header H2 { +"Icon search" }
            IconSearch()
        }
    }
}
