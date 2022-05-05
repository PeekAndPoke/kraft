package de.peekandpoke.kraft.examples.semanticui.helpers

import de.peekandpoke.kraft.addons.styling.css
import de.peekandpoke.ultra.semanticui.icon
import de.peekandpoke.ultra.semanticui.noui
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.css.*
import kotlinx.css.properties.border
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

fun FlowContent.kotlinToHtml(
    kotlin: String,
    html: String,
) {
    fun PRE.style() {
        css {
            padding(16.px)
            backgroundColor = Color("#FAFAFA")
            border(1.px, BorderStyle.solid, Color("#E0E0E0"))
            borderRadius = 5.px
        }
    }

    ui.segment {
        ui.two.column.very.relaxed.grid {
            noui.column {
                ui.header { +"Kotlin" }
                pre {
                    style()
                    +kotlin
                }
            }

            noui.column {
                ui.header { +"HTML" }
                pre {
                    style()
                    +html
                }
            }
        }
        ui.vertical.divider { +">>" }
    }
}
