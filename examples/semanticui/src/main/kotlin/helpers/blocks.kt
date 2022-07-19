package de.peekandpoke.kraft.examples.semanticui.helpers

import de.peekandpoke.kraft.addons.prismjs.PrismHtml
import de.peekandpoke.kraft.addons.prismjs.PrismKotlin
import de.peekandpoke.kraft.addons.styling.css
import de.peekandpoke.kraft.semanticui.icon
import de.peekandpoke.kraft.semanticui.noui
import de.peekandpoke.kraft.semanticui.ui
import kotlinx.css.BorderStyle
import kotlinx.css.Color
import kotlinx.css.borderRadius
import kotlinx.css.properties.border
import kotlinx.css.px
import kotlinx.html.*

fun FlowContent.readTheDocs(url: String) {
    ui.message {
        icon.book()

        a(href = url, target = "_blank") {
            +"Read the docs in Fomantic-UI"
        }
    }
}

fun FlowContent.example(block: DIV.() -> Unit) {
    div("example") {
        block()
    }
}

fun FlowContent.shortParagraphWireFrame() {
    p {
        ui.with("wireframe").image Img {
            src = "images/wireframe/short-paragraph.png"
        }
    }
}

fun CommonAttributeGroupFacade.applyCodeBlockStyle() {
    css {
        border(1.px, BorderStyle.solid, Color("#E0E0E0"))
        borderRadius = 5.px
    }
}

fun FlowContent.kotlinToHtml(
    kotlin: String,
    html: String,
) {
    ui.segment {
        ui.two.column.very.relaxed.grid {
            noui.column {
                ui.header { +"Kotlin" }
                PrismKotlin(kotlin)
            }

            noui.column {
                ui.header { +"HTML" }
                PrismHtml(html)
            }
        }
        ui.vertical.divider { +">>" }
    }
}
