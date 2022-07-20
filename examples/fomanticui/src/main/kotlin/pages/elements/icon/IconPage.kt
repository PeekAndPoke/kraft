package de.peekandpoke.kraft.examples.fomanticui.pages.elements.icon

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.examples.fomanticui.helpers.readTheDocs
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.Tag

@Suppress("FunctionName")
fun Tag.IconPage() = comp {
    IconPage(it)
}

class IconPage(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        ui.basic.segment {
            ui.dividing.header H1 { +"Icon" }

            readTheDocs("https://fomantic-ui.com/elements/icon.html#/definition")

            IconExamples()

            ui.dividing.header H2 { +"Icon search" }
            IconSearch()
        }
    }
}
