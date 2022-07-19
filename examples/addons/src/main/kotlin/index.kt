package de.peekandpoke.kraft.examples.jsaddons

import de.peekandpoke.kraft.vdom.preact.PreactVDomEngine
import kotlinx.browser.document
import org.w3c.dom.HTMLElement

fun main() {
    val mountPoint = document.getElementById("spa") as HTMLElement

    PreactVDomEngine(mountPoint) {
        Examples()
    }
}
