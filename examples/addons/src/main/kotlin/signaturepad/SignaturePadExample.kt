package de.peekandpoke.kraft.examples.jsaddons.signaturepad

import de.peekandpoke.kraft.addons.signaturepad.SignaturePad
import de.peekandpoke.kraft.addons.styling.css
import de.peekandpoke.kraft.components.*
import de.peekandpoke.kraft.semanticui.icon
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

    private val sigPadRef = ComponentRef.Tracker<SignaturePad>()

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        ui.segment {
            ui.header H2 { +"Signature Pad" }

            div {
                css {
                    position = Position.relative
                    border(2.px, BorderStyle.dashed, Color.lightGrey)
                    backgroundColor = Color.white
                    height = 30.vh
                }

                SignaturePad {
                    dataPng = it.getPng()
                    dataPngTrimmed = it.getPngTrimmed()

                    dataSvg = it.getSvg()
                    dataSvgTrimmed = it.getSvgTrimmed()

                    dataJpg = it.getJpg(0.5)
                    dataJpgTrimmed = it.getJpgTrimmed(0.5)
                }.track(sigPadRef)

                sigPadRef { sigPad ->
                    if (sigPad.isNotEmpty()) {
                        div {
                            css {
                                position = Position.absolute
                                right = 1.px
                                bottom = 1.px
                            }

                            ui.big.tertiary.icon.button {
                                onClick {
                                    sigPad.clear()
                                }
                                icon.eraser()
                            }
                        }
                    }
                }
            }

            sigPadRef { pad ->

                ui.divider {}

                ui.button {
                    onClick { pad.clear() }
                    icon.times()
                    +"Clear"
                }

                if (pad.isEmpty()) {
                    ui.red.label { +"Empty" }
                } else {
                    ui.green.label { +"Not empty" }
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
