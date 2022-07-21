package de.peekandpoke.kraft.examples.fomanticui.pages.elements.divider

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.examples.fomanticui.helpers.HorizontalContentAndCode
import de.peekandpoke.kraft.examples.fomanticui.helpers.example
import de.peekandpoke.kraft.examples.fomanticui.helpers.readTheDocs
import de.peekandpoke.kraft.examples.fomanticui.helpers.shortParagraphWireFrame
import de.peekandpoke.kraft.semanticui.icon
import de.peekandpoke.kraft.semanticui.noui
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.common.fixture.LoremIpsum
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
            ui.header H1 { +"Divider" }

            readTheDocs("https://fomantic-ui.com/elements/divider.html#/definition")

            ui.dividing.header H2 { +"Types" }

            renderStandard()
            renderVertical()
            renderHorizontal()
            renderHorizontalAlignment()

            ui.dividing.header H2 { +"Variations" }

            renderInverted()
            renderFitted()
        }
    }

    private fun FlowContent.renderStandard() = example {
        ui.header H2 { +"Standard Divider" }

        HorizontalContentAndCode(
            ExtractedCodeBlocks.pages_elements_divider_DividerPage_kt_renderStandard
        ) {
            // <CodeBlock renderStandard>
            ui.segment {

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

    private fun FlowContent.renderHorizontal() = example {
        ui.header H2 { +"Horizontal Divider" }

        HorizontalContentAndCode(
            ExtractedCodeBlocks.pages_elements_divider_DividerPage_kt_renderHorizontal_1,
        ) {
            // <CodeBlock renderHorizontal_1>
            shortParagraphWireFrame()

            ui.horizontal.divider { +"or" }

            shortParagraphWireFrame()
            // </CodeBlock>
        }

        HorizontalContentAndCode(
            ExtractedCodeBlocks.pages_elements_divider_DividerPage_kt_renderHorizontal_2,
        ) {
            // <CodeBlock renderHorizontal_2>
            ui.horizontal.divider.header {
                icon.tag()
                +"Description"
            }

            shortParagraphWireFrame()

            ui.horizontal.divider.header {
                icon.chart_bar()
                +"Specification"
            }

            shortParagraphWireFrame()
            // </CodeBlock>
        }
    }

    private fun FlowContent.renderHorizontalAlignment() = example {
        ui.header H2 { +"Horizontal Alignment" }

        HorizontalContentAndCode(
            ExtractedCodeBlocks.pages_elements_divider_DividerPage_kt_renderHorizontalAlignment_1,
        ) {
            // <CodeBlock renderHorizontalAlignment_1>
            ui.horizontal.left.aligned.divider {
                icon.align_left()
                +"Left aligned"
            }
            ui.horizontal.center.aligned.divider {
                icon.align_center()
                +"Center aligned"
            }
            ui.horizontal.right.aligned.divider {
                icon.align_center()
                +"Right aligned"
            }
            // </CodeBlock>
        }
    }

    private fun FlowContent.renderInverted() = example {
        ui.header H2 { +"Inverted" }

        HorizontalContentAndCode(
            ExtractedCodeBlocks.pages_elements_divider_DividerPage_kt_renderInverted_1,
        ) {
            // <CodeBlock renderInverted_1>
            ui.inverted.segment {
                shortParagraphWireFrame()

                ui.inverted.divider { }

                shortParagraphWireFrame()

                ui.inverted.horizontal.divider { +"or" }

                shortParagraphWireFrame()
            }

            // </CodeBlock>
        }
    }

    private fun FlowContent.renderFitted() = example {
        ui.header H2 { +"Fitted" }

        HorizontalContentAndCode(
            ExtractedCodeBlocks.pages_elements_divider_DividerPage_kt_renderFitted,
        ) {
            // <CodeBlock renderFitted>
            +LoremIpsum(words = 50)

            ui.fitted.divider { }

            +LoremIpsum(words = 50)
            // </CodeBlock>
        }
    }
}
