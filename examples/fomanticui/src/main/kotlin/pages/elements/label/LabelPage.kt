package de.peekandpoke.kraft.examples.fomanticui.pages.elements.label

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.examples.fomanticui.helpers.HorizontalContentAndCode
import de.peekandpoke.kraft.examples.fomanticui.helpers.example
import de.peekandpoke.kraft.examples.fomanticui.helpers.readTheFomanticUiDocs
import de.peekandpoke.kraft.semanticui.icon
import de.peekandpoke.kraft.semanticui.noui
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import generated.ExtractedCodeBlocks
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.img
import kotlinx.html.p

@Suppress("FunctionName")
fun Tag.LabelPage() = comp {
    LabelPage(it)
}

class LabelPage(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        ui.basic.segment {
            ui.dividing.header H1 { +"Label" }

            readTheFomanticUiDocs("https://fomantic-ui.com/elements/label.html")

            ui.dividing.header H2 { +"Types" }

            renderLabel()
            renderImageLabel()
        }
    }

    private fun FlowContent.renderLabel() = example {
        ui.header H3 { +"Label" }

        p { +"A label" }

        HorizontalContentAndCode(
            ExtractedCodeBlocks.pages_elements_label_LabelPage_kt_renderLabel,
        ) {
            // <CodeBlock renderLabel>
            ui.label {
                icon.mail()
                +"23"
            }
            // </CodeBlock>
        }
    }

    private fun FlowContent.renderImageLabel() = example {
        ui.header H3 { +"Image" }

        p { +"A label can be formatted to emphasize an image" }

        HorizontalContentAndCode(
            ExtractedCodeBlocks.pages_elements_label_LabelPage_kt_renderImageLabel_1,
        ) {
            // <CodeBlock renderImageLabel_1>
            ui.image.label {
                img { src = "images/avatar2/large/elyse.png" }
                +"Elyse"
            }
            ui.image.label {
                img { src = "images/avatar2/large/elliot.jpg" }
                +"Elliot"
            }
            ui.image.label {
                img { src = "images/avatar2/large/molly.png" }
                +"Molly"
            }
            // </CodeBlock>
        }

        HorizontalContentAndCode(
            ExtractedCodeBlocks.pages_elements_label_LabelPage_kt_renderImageLabel_2,
        ) {
            // <CodeBlock renderImageLabel_2>
            ui.blue.image.label A {
                img { src = "images/avatar2/large/elyse.png" }
                +"Elyse"
                noui.detail { +"Friend" }
            }
            ui.green.image.label A {
                img { src = "images/avatar2/large/elliot.jpg" }
                +"Elliot"
                noui.detail { +"Student" }
            }
            ui.orange.image.label A {
                img { src = "images/avatar2/large/molly.png" }
                +"Molly"
                noui.detail { +"Co-worker" }
            }
            // </CodeBlock>
        }

        HorizontalContentAndCode(
            ExtractedCodeBlocks.pages_elements_label_LabelPage_kt_renderImageLabel_3,
        ) {
            // <CodeBlock renderImageLabel_3>
            ui.image.label {
                img { src = "images/avatar2/large/elyse.png" }
                +"Elyse"
                icon.delete()
            }
            ui.image.label {
                img { src = "images/avatar2/large/elliot.jpg" }
                +"Elliot"
                icon.green.delete()
            }
            ui.image.label {
                img { src = "images/avatar2/large/molly.png" }
                +"Molly"
                icon.red.delete()
            }
            // </CodeBlock>
        }
    }
}
