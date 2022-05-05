package de.peekandpoke.kraft.examples.semanticui.pages.elements.icon

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.semanticui.icon
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.Tag
import kotlinx.html.a

@Suppress("FunctionName")
fun Tag.IconPage() = comp {
    IconPage(it)
}

class IconPage(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        ui.basic.padded.segment {
            ui.dividing.header H1 { +"Icons" }

            ui.green.message {
                icon.exclamation()

                a(href = "https://fomantic-ui.com/elements/icon.html#/definition", target = "_blank") {
                    +"Read the docs"
                }
            }

            IconExamples()

            ui.dividing.header H2 { +"Icon search" }
            IconSearch()
        }
    }
}
