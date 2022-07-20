package de.peekandpoke.kraft.examples.fomanticui.helpers

import de.peekandpoke.kraft.addons.prismjs.PrismKotlin
import de.peekandpoke.kraft.addons.prismjs.PrismPlugin.CopyToClipboard.Companion.copyToClipboard
import de.peekandpoke.kraft.addons.prismjs.PrismPlugin.LineNumbers.Companion.lineNumbers
import de.peekandpoke.kraft.addons.prismjs.PrismPlugin.ShowLanguage.Companion.showLanguage
import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.components.Ctx
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.FlowContent
import kotlinx.html.Tag

@Suppress("FunctionName")
fun Tag.ContentAndCode(
    code: String,
    example: FlowContent.() -> Unit
) = comp(
    ContentAndCode.Props(
        code = code,
        example = example,
    )
) {
    ContentAndCode(it)
}

class ContentAndCode(ctx: Ctx<Props>) : Component<ContentAndCode.Props>(ctx) {

    //  PROPS  //////////////////////////////////////////////////////////////////////////////////////////////////

    data class Props(
        val code: String,
        val example: FlowContent.() -> Unit,

        )

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {

        ui.two.column.grid {

            ui.column {
                props.example(this)
            }

            ui.column {
                PrismKotlin(props.code.trimIndent()) {
                    lineNumbers()
                    showLanguage()
                    copyToClipboard()
                }
            }
        }
    }
}
