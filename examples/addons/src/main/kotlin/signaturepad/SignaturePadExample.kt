package de.peekandpoke.kraft.examples.jsaddons.signaturepad

import de.peekandpoke.kraft.addons.signaturepad.SignaturePad
import de.peekandpoke.kraft.addons.styling.css
import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.img

@Suppress("FunctionName")
fun Tag.SignaturePadExample() = comp {
    SignaturePadExample(it)
}

class SignaturePadExample(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    private var dataPng: String? by value(null)
    private var dataPngTrimmed: String? by value(null)
    private var dataSvg: String? by value(null)
    private var dataSvgTrimmed: String? by value(null)
    private var dataJpg: String? by value(null)
    private var dataJpgTrimmed: String? by value(null)

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        ui.segment {
            ui.header H2 { +"Signature Pad" }

            div {
                css {
                    width = 100.pct
                    height = 20.vh
                    backgroundColor = Color.lightGrey
                }

                SignaturePad {
                    dataPng = it.getPng()
                    dataPngTrimmed = it.getPngTrimmed()

                    dataSvg = it.getSvg()
                    dataSvgTrimmed = it.getSvgTrimmed()

                    dataJpg = it.getJpg(0.5)
                    dataJpgTrimmed = it.getJpgTrimmed(0.5)
                }
            }

            ui.divider {}

            ui.two.column.grid {

                listOf(
                    "PNG" to dataPng,
                    "PNG trimmed" to dataPngTrimmed,
                    "SVG" to dataSvg,
                    "SVG trimmed" to dataSvgTrimmed,
                    "JPG" to dataJpg,
                    "JPG trimmed" to dataJpgTrimmed,
                ).forEach { (name, data) ->

                    ui.column {

                        ui.header { +name }

                        ui.fitted.image {

                            css {
                                border(width = 1.px, style = BorderStyle.dotted, color = Color.gray)
                            }

                            data?.let {
                                img { src = data }
                            }
                        }
                    }
                }
            }
        }
    }
}
