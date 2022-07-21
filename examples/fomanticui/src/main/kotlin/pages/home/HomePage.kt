package de.peekandpoke.kraft.examples.fomanticui.pages.home

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.examples.fomanticui.helpers.example
import de.peekandpoke.kraft.examples.fomanticui.helpers.kotlinToHtml
import de.peekandpoke.kraft.semanticui.icon
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.p

@Suppress("FunctionName")
fun Tag.HomePage() = comp {
    HomePage(it)
}

class HomePage(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        ui.basic.segment {
            ui.header H1 {
                +"FomanticUI + K.R.A.F.T. = "
                icon.red.heart_outline()
            }

            renderFomanticUiExamplesLink()

            renderDslIntro()
        }
    }

    private fun FlowContent.renderFomanticUiExamplesLink() = example {
        ui.header H2 { +"FomanticUI + Kraft code examples" }

        ui.big.green.basic.button A {
            href = "https://github.com/PeekAndPoke/kraft/tree/master/examples/semanticui"
            target = "_blank"
            icon.github()
            +"Show me the code!"
        }
    }

    private fun FlowContent.renderDslIntro() = example {
        ui.header H2 { +"Intro to the DSL" }

        p {
            +"Fomantic-UI often times needs the css classes to be prefixed with "
            ui.basic.label { +"ui" }
        }

        kotlinToHtml(
            kotlin = """
                ui.basic.segment {
                    +"Hello World"
                }
            """.trimIndent(),
            html = """
                <div class="ui basic segment">
                    Hello World
                </div>
            """.trimIndent()
        )

        p {
            +"But there are also cases where you want to omit this prefix. Then you can use "
            ui.basic.label { +"noui" }
        }

        kotlinToHtml(
            kotlin = """
                noui.basic.item {
                    +"Hello World"
                }
            """.trimIndent(),
            html = """
                <div class="basic item">
                    Hello World
                </div>
            """.trimIndent()
        )

        p {
            +"Sometimes you want to change the html tag from div to something else"
        }

        kotlinToHtml(
            kotlin = """
                no.basic.item A {
                    href = "https://github.com/PeekAndPoke/kraft"
                    +"K.R.A.F.T."
                }
            """.trimIndent(),
            html = """
                <a class="basic item" href="https://github.com/PeekAndPoke/kraft">
                    K.R.A.F.T.
                </a>
            """.trimIndent()
        )
    }
}
