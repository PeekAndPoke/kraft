package de.peekandpoke.kraft.examples.jsaddons

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.semanticui.css
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
        renderAddonCards()

        ui.divider()

        renderCoreCards()
    }

    private fun FlowContent.renderAddonCards() {
        ui.four.doubling.link.cards {

            noui.card A {
                href = routes.avatars.index()

                noui.center.aligned.content {
                    renderLogo(
                        src = "https://raw.githubusercontent.com/laurentpayot/minidenticons/HEAD/alienHead66.svg",
                        alt = "Avatars",
                    )
                }
                noui.center.aligned.content {
                    +"Avatars"
                }
            }

            noui.card A {
                href = routes.browserDetect()

                noui.center.aligned.content {
                    renderLogo(
                        src = "images/browsers.jpg",
                        alt = "Browser Detect",
                    )
                }
                noui.center.aligned.content {
                    +"Browser Detect"
                }
            }

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
                href = routes.pdfjs.index()

                noui.center.aligned.content {
                    renderLogo(
                        src = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Pdf-js_logo.svg/800px-Pdf-js_logo.svg.png",
                        alt = "PDF JS",
                    )
                }
                noui.center.aligned.content {
                    +"PDF JS"
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

            noui.card A {
                href = routes.signaturePad()

                noui.center.aligned.content {
                    renderLogo(
                        src = "https://camo.githubusercontent.com/fcd5a5ab2be5419d00fcb803f14c55652cf60696d7f6d9828b99c1783d9f14a3/68747470733a2f2f662e636c6f75642e6769746875622e636f6d2f6173736574732f393837332f3236383034362f39636564333435342d386566632d313165322d383136652d6139623137306135313030342e706e67",
                        alt = "Signature Pad",
                    )
                }
                noui.center.aligned.content {
                    +"Signature Pad"
                }
            }
        }
    }

    private fun FlowContent.renderCoreCards() {
        ui.four.doubling.cards {
            noui.card A {
                href = routes.core.scriptLoader()

                noui.center.aligned.content {
                    +"Script Loader"
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
