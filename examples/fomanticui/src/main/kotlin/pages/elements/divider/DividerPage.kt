package de.peekandpoke.kraft.examples.fomanticui.pages.elements.divider

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.examples.fomanticui.helpers.HorizontalContentAndCode
import de.peekandpoke.kraft.examples.fomanticui.helpers.example
import de.peekandpoke.kraft.examples.fomanticui.helpers.readTheDocs
import de.peekandpoke.kraft.examples.fomanticui.helpers.shortParagraphWireFrame
import de.peekandpoke.kraft.semanticui.noui
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import generated.ExtractedCodeBlocks
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

    private fun FlowContent.renderStandard() = example {
        ui.header H2 { +"Standard Divider" }

        HorizontalContentAndCode(
            ExtractedCodeBlocks.pages_elements_divider_DividerPage_kt_renderStandard
        ) {
            // <CodeBlock renderStandard>
            ui.segment {
                shortParagraphWireFrame()
                ui.divider {}
                shortParagraphWireFrame()
            }
            // </CodeBlock>
        }
    }

    private fun FlowContent.renderVertical() = example {
        ui.header H2 { +"Vertical Divider" }

        HorizontalContentAndCode(
            ExtractedCodeBlocks.pages_elements_divider_DividerPage_kt_renderVertical,
        ) {
            // <CodeBlock renderVertical>
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
            // </CodeBlock>
        }
    }
}
