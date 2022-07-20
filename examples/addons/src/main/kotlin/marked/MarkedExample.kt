package de.peekandpoke.kraft.examples.jsaddons.marked

import de.peekandpoke.kraft.addons.marked.marked
import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.Tag
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.unsafe

@Suppress("FunctionName")
fun Tag.MarkedExample() = comp {
    MarkedExample(it)
}

class MarkedExample(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
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
