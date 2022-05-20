package de.peekandpoke.kraft.examples.semanticui.pages.elements.button

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.onClick
import de.peekandpoke.kraft.examples.semanticui.helpers.example
import de.peekandpoke.kraft.examples.semanticui.helpers.readTheDocs
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
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

        example {
            ui.header { +"Button" }

            p { +"A standard Button" }

            ui.button {
                onClick {
                    buttonState = !buttonState
                    console.log("buttonState", buttonState)
                }

                when (buttonState) {
                    false -> +"Follow"
                    else -> +"Following"
                }
            }
        }
    }
}
