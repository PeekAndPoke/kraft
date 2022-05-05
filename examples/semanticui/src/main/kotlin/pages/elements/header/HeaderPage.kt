package de.peekandpoke.kraft.examples.semanticui.pages.elements.header

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.examples.semanticui.helpers.example
import de.peekandpoke.kraft.examples.semanticui.helpers.readTheDocs
import de.peekandpoke.kraft.examples.semanticui.helpers.shortParagraphWireFrame
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.FlowContent
import kotlinx.html.Tag

@Suppress("FunctionName")
fun Tag.HeaderPage() = comp {
    HeaderPage(it)
}

class HeaderPage(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        ui.basic.segment {
            ui.dividing.header H1 { +"Header" }

            readTheDocs("https://fomantic-ui.com/elements/header.html")

            renderPageHeaders()

        }
    }

    private fun FlowContent.renderPageHeaders() {
        example {

            ui.dividing.header H2 { +"Page headers" }

            ui.header H1 { +"First header" }
            shortParagraphWireFrame()

            ui.header H2 { +"Seconds header" }
            shortParagraphWireFrame()

            ui.huge.red.label { +"TODO" }
        }
    }
}
