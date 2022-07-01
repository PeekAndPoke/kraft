package de.peekandpoke.kraft.examples.jsaddons

import de.peekandpoke.kraft.addons.marked.marked
import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.*

@Suppress("FunctionName")
fun Tag.Examples() = comp {
    Examples(it)
}

class Examples(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {

        ChartJsExample()

        KonvaExample()

        markedExample()
    }

    private fun FlowContent.graphJsExample() {
        ui.segment {
            ui.header { +"Graph JS" }

        }
    }

    private fun FlowContent.markedExample() {
        ui.segment {
            ui.header H2 { +"Marked JS" }

            div {
                a(href = "https://www.npmjs.com/package/marked", target = "_blank") {
                    +"https://www.npmjs.com/package/marked"
                }
            }

            ui.segment {
                unsafe {
                    +marked.parse(
                        """
# Header 1                         
## Header 2                         
### Header 3                         
                    """.trimIndent()
                    )
                }
            }

        }
    }
}
