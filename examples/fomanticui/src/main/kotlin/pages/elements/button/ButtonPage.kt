package de.peekandpoke.kraft.examples.fomanticui.pages.elements.button

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.onClick
import de.peekandpoke.kraft.examples.fomanticui.helpers.ContentAndCode
import de.peekandpoke.kraft.examples.fomanticui.helpers.example
import de.peekandpoke.kraft.examples.fomanticui.helpers.readTheDocs
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import generated.ExtractedCodeBlocks
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.p

@Suppress("FunctionName")
fun Tag.ButtonPage() = comp {
    ButtonPage(it)
}

class ButtonPage(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    private var buttonState: Boolean by value(false)

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        ui.basic.segment {
            ui.dividing.header H1 { +"Button" }

            readTheDocs("https://fomantic-ui.com/elements/button.html#/definition")

            renderStandardButton()
        }
    }

    private fun FlowContent.renderStandardButton() {

        ui.header { +"Button" }

        example {

            p { +"A standard Button" }

            ContentAndCode(
                ExtractedCodeBlocks.pages_elements_button_ButtonPage_kt_standardButton,
            ) {
                // <CodeBlock standardButton>
                ui.button {
                    onClick {
                        buttonState = !buttonState
                    }

                    when (buttonState) {
                        true -> +"Following"
                        false -> +"Follow"
                    }
                }
                // </CodeBlock>
            }
        }
    }
}
