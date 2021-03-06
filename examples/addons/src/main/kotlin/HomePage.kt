package de.peekandpoke.kraft.examples.jsaddons

import de.peekandpoke.kraft.addons.styling.css
import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.semanticui.noui
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.css.height
import kotlinx.css.px
import kotlinx.html.FlowContent
import kotlinx.html.Tag

@Suppress("FunctionName")
fun Tag.HomePage() = comp {
    HomePage(it)
}

class HomePage(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        ui.four.link.cards {

            noui.card A {
                href = routes.chartJs()

                noui.center.aligned.content {
                    renderLogo(
                        src = "https://www.chartjs.org/img/chartjs-logo.svg",
                        alt = "ChartJS Logo",
                    )
                }
                noui.center.aligned.content {
                    +"ChartJS"
                }
            }

            noui.card A {
                href = routes.jwtDecode()

                noui.center.aligned.content {
                    renderLogo(
                        src = "https://user-images.githubusercontent.com/83319/31722733-de95bbde-b3ea-11e7-96bf-4f4e8f915588.png",
                        alt = "JWT Decode Logo",
                    )
                }
                noui.center.aligned.content {
                    +"JWT Decode"
                }
            }

            noui.card A {
                href = routes.konva()

                noui.center.aligned.content {
                    renderLogo(
                        src = "https://camo.githubusercontent.com/8ee88dda37d12638ee55035971e01ce818f0564584264cd9a688188846ce7e34/68747470733a2f2f6b6f6e76616a732e6f72672f616e64726f69642d6368726f6d652d313932783139322e706e67",
                        alt = "Konva Logo",
                    )
                }
                noui.center.aligned.content {
                    +"Konva"
                }
            }

            noui.card A {
                href = routes.marked()

                noui.center.aligned.content {
                    renderLogo(
                        src = "https://marked.js.org/img/logo-black.svg",
                        alt = "Marked Logo",
                    )
                }
                noui.center.aligned.content {
                    +"Marked"
                }
            }

            noui.card A {
                href = routes.prismjs()

                noui.center.aligned.content {
                    renderLogo(
                        src = "https://pbs.twimg.com/profile_images/2451426554/Screen_Shot_2012-07-31_at_21.57.03__400x400.png",
                        alt = "PrismJs Logo",
                    )
                }
                noui.center.aligned.content {
                    +"PrismJs"
                }
            }
        }
    }

    private fun FlowContent.renderLogo(src: String, alt: String) {
        ui.image Img {
            css {
                height = 100.px
            }
            this.src = src
            this.alt = alt
        }
    }
}
