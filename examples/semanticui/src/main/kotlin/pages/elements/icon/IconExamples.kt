package de.peekandpoke.kraft.examples.semanticui.pages.elements.icon

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.semanticui.icon
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div

@Suppress("FunctionName")
fun Tag.IconExamples() = comp {
    IconExamples(it)
}

class IconExamples(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {

        renderSizes()

        renderColors()

        renderSpecials()

        renderIconGroupsAndCorners()
    }

    private fun FlowContent.renderSizes() {

        ui.segment {
            ui.header H3 { +"Sizes" }

            ui.eight.column.middle.aligned.grid {
                ui.center.aligned.column {
                    icon.mini.chess()
                    div { +"mini" }
                }
                ui.center.aligned.column {
                    icon.tiny.chess()
                    div { +"tiny" }
                }
                ui.center.aligned.column {
                    icon.small.chess()
                    div { +"small" }
                }
                ui.center.aligned.column {
                    icon.medium.chess()
                    div { +"medium" }
                }
                ui.center.aligned.column {
                    icon.large.chess()
                    div { +"large" }
                }
                ui.center.aligned.column {
                    icon.big.chess()
                    div { +"big" }
                }
                ui.center.aligned.column {
                    icon.huge.chess()
                    div { +"huge" }
                }
                ui.center.aligned.column {
                    icon.massive.chess()
                    div { +"massive" }
                }
            }
        }
    }

    private fun FlowContent.renderColors() {

        ui.segment {
            ui.header H3 { +"Colors" }

            ui.eight.column.grid {
                ui.center.aligned.column {
                    icon.large.red.couch()
                    div { +"red" }
                }
                ui.center.aligned.column {
                    icon.large.orange.couch()
                    div { +"orange" }
                }
                ui.center.aligned.column {
                    icon.large.yellow.couch()
                    div { +"yellow" }
                }
                ui.center.aligned.column {
                    icon.large.olive.couch()
                    div { +"olive" }
                }
                ui.center.aligned.column {
                    icon.large.green.couch()
                    div { +"green" }
                }
                ui.center.aligned.column {
                    icon.large.teal.couch()
                    div { +"teal" }
                }
                ui.center.aligned.column {
                    icon.large.blue.couch()
                    div { +"blue" }
                }
                ui.center.aligned.column {
                    icon.large.violet.couch()
                    div { +"violet" }
                }
                ui.center.aligned.column {
                    icon.large.purple.couch()
                    div { +"purple" }
                }
                ui.center.aligned.column {
                    icon.large.pink.couch()
                    div { +"pink" }
                }
                ui.center.aligned.column {
                    icon.large.brown.couch()
                    div { +"brown" }
                }
                ui.center.aligned.column {
                    icon.large.grey.couch()
                    div { +"grey" }
                }
                ui.center.aligned.column {
                    icon.large.black.couch()
                    div { +"black" }
                }
                ui.center.aligned.column {
                    icon.large.white.couch()
                    div { +"white" }
                }
                ui.center.aligned.inverted.blue.column {
                    icon.large.white.couch()
                    div { +"white" }
                }
            }
        }
    }

    private fun FlowContent.renderSpecials() {
        ui.segment {
            ui.header H3 { +"Features" }

            ui.eight.column.grid {
                ui.center.aligned.column {
                    icon.disabled.big.red.couch()
                    div { +"disabled" }
                }
                ui.center.aligned.column {
                    icon.big.loading.spinner()
                    icon.big.loading.circle_notch()
                    icon.big.loading.asterisk()
                    icon.big.loading.user()
                    div { +"loading" }
                }

                ui.center.aligned.column {
                    +"Fitted"
                    icon.fitted.globe()
                    +"icon"
                }
                ui.center.aligned.column {
                    icon.big.blue.link.question()
                    div { +"link" }
                }
                ui.center.aligned.column {
                    icon.big.horizontally.flipped.question()
                    icon.big.vertically.flipped.question()
                    div { +"flipped" }
                }
                ui.center.aligned.column {
                    icon.big.clockwise.rotated.question()
                    icon.big.counterclockwise.rotated.question()
                    div { +"rotated" }
                }
                ui.center.aligned.column {
                    icon.circular.users()
                    icon.circular.teal.users()
                    icon.circular.inverted.users()
                    icon.circular.inverted.teal.users()
                    div { +"circular" }
                }
                ui.center.aligned.column {
                    icon.circular.colored.red.users()
                    icon.circular.colored.green.users()
                    icon.circular.colored.blue.users()
                    div { +"colored circular" }
                }
                ui.center.aligned.column {
                    icon.bordered.users()
                    icon.bordered.teal.users()
                    icon.bordered.inverted.users()
                    icon.bordered.inverted.teal.users()
                    div { +"bordered" }
                }
                ui.center.aligned.column {
                    icon.bordered.colored.red.users()
                    icon.bordered.colored.green.users()
                    icon.bordered.colored.blue.users()
                    div { +"colored bordered" }
                }
            }
        }
    }

    private fun FlowContent.renderIconGroupsAndCorners() {

        ui.segment {
            ui.header H3 { +"Grouped icons & cornered icons" }

            ui.eight.column.grid {
                ui.center.aligned.column {
                    ui.big.icons I {
                        icon.big.green.circle_outline()
                        icon.user()
                    }
                }
                ui.center.aligned.column {
                    ui.big.icons I {
                        icon.big.red.dont.render()
                        icon.user()
                    }
                }
                ui.center.aligned.column {
                    ui.big.icons I {
                        icon.user()
                        icon.top.left.corner.plus()
                    }
                }
                ui.center.aligned.column {
                    ui.big.icons I {
                        icon.user()
                        icon.top.right.corner.plus()
                    }
                }
                ui.center.aligned.column {
                    ui.big.icons I {
                        icon.user()
                        icon.bottom.right.corner.plus()
                    }
                }
                ui.center.aligned.column {
                    ui.big.icons I {
                        icon.user()
                        icon.bottom.left.corner.plus()
                    }
                }
                ui.center.aligned.column {
                    ui.big.icons I {
                        icon.twitter()
                        icon.bottom.right.corner.inverted.plus()
                    }
                }
            }
        }
    }
}
