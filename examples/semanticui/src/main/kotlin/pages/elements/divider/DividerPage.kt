package de.peekandpoke.kraft.examples.semanticui.pages.elements.divider

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.examples.semanticui.helpers.ExampleWithCode
import de.peekandpoke.kraft.examples.semanticui.helpers.readTheDocs
import de.peekandpoke.kraft.examples.semanticui.helpers.shortParagraphWireFrame
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.semanticui.noui
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.FlowContent
import kotlinx.html.Tag

@Suppress("FunctionName")
fun Tag.DividerPage() = comp {
    DividerPage(it)
}

class DividerPage(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        ui.basic.segment {
            ui.dividing.header H1 { +"Divider" }

            readTheDocs("https://fomantic-ui.com/elements/divider.html#/definition")

            renderStandard()

            renderVertical()
        }
    }

    private fun FlowContent.renderStandard() {
        ExampleWithCode(
            """
                ui.divider {}
            """.trimIndent()
        ) {
            ui.header H2 { +"Standard Divider" }

            shortParagraphWireFrame()
            ui.divider {}
            shortParagraphWireFrame()
        }
    }

    private fun FlowContent.renderVertical() {
        ExampleWithCode(
            """
                ui.segment {
                    ui.two.column.very.relaxed.grid {
                        noui.column {
                            p {}
                            p {}
                        }
                        noui.column {
                            p {}
                            p {}
                        }
                    }
                    ui.vertical.divider { +"and" }
                }
            """.trimIndent()
        ) {
            ui.header H2 { +"Vertical Divider" }

            ui.segment {
                ui.two.column.very.relaxed.grid {
                    noui.column {
                        shortParagraphWireFrame()
                        shortParagraphWireFrame()
                    }
                    noui.column {
                        shortParagraphWireFrame()
                        shortParagraphWireFrame()
                    }
                }
                ui.vertical.divider { +"and" }
            }
        }
    }
}
