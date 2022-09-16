package de.peekandpoke.kraft.examples.jsaddons.core

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.onClick
import de.peekandpoke.kraft.semanticui.icon
import de.peekandpoke.kraft.semanticui.noui
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.utils.ScriptLoader
import de.peekandpoke.kraft.utils.launch
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.Tag
import kotlinx.html.p

@Suppress("FunctionName")
fun Tag.ScriptLoaderExample() = comp {
    ScriptLoaderExample(it)
}

class ScriptLoaderExample(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    private val javascripts by subscribingTo(ScriptLoader.javascripts)

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        ui.header H2 { +"KRAFT Core examples" }

        ui.header H3 { +"Script Loader" }

        p { +"Loaded scripts" }

        ui.divided.list {
            javascripts.forEach { script ->
                noui.item {
                    ui.horizontal.list {
                        noui.item {
                            +script.src
                        }
                        noui.item {
                            ui.icon.button {
                                onClick {
                                    ScriptLoader.unload(script)
                                }
                                icon.trash()
                            }
                        }
                    }
                }
            }
        }

        p { +"Load scripts" }

        ui.button {
            onClick {
                launch {
                    ScriptLoader.loadJavascriptAsync("https://js.stripe.com/v3/").await()
                }
            }
            +"Load Stripe"
        }

        ui.button {
            onClick {
                launch {
                    ScriptLoader.loadAsync(
                        ScriptLoader.Javascript(
                            src = "https://code.jquery.com/jquery-3.6.1.min.js",
                            integrity = "sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=",
                            crossOrigin = "anonymous"

                        )
                    ).await()
                }
            }
            +"Load jQuery"
        }
    }
}
