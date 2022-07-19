package de.peekandpoke.kraft.examples.semanticui.helpers

import de.peekandpoke.kraft.addons.prismjs.PrismKotlin
import de.peekandpoke.kraft.addons.semanticui.components.Collapsable
import de.peekandpoke.kraft.addons.semanticui.components.CollapsableComponent
import de.peekandpoke.kraft.components.*
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.a

@Suppress("FunctionName")
fun Tag.ExampleWithCode(
    code: String,
    example: FlowContent.() -> Unit
) = comp(
    ExampleWithCode.Props(
        code = code,
        example = example,
    )
) {
    ExampleWithCode(it)
}

class ExampleWithCode(ctx: Ctx<Props>) : Component<ExampleWithCode.Props>(ctx) {

    //  PROPS  //////////////////////////////////////////////////////////////////////////////////////////////////

    data class Props(
        val code: String,
        val example: FlowContent.() -> Unit,

        )

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    private val tracker = ComponentRef.Tracker<CollapsableComponent>()

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {

        example {
            props.example(this)

            Collapsable {
                header { ctx ->
                    a(href = "#") {
                        onClick {
                            it.preventDefault()
                            ctx.toggle()
                        }
                        +"Show code"
                    }
                }
                content {
                    PrismKotlin(props.code.trimIndent())
                }
            }.trackRef(tracker)
        }
    }
}
