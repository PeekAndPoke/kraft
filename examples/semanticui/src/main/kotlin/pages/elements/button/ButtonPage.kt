package de.peekandpoke.kraft.examples.semanticui.pages.elements.button

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.examples.semanticui.helpers.readTheDocs
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.Tag

@Suppress("FunctionName")
fun Tag.ButtonPage() = comp {
    ButtonPage(it)
}

class ButtonPage(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        ui.basic.segment {
            ui.dividing.header H1 { +"Button" }

            readTheDocs("https://fomantic-ui.com/elements/button.html#/definition")
        }
    }
}