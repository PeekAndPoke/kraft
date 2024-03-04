package de.peekandpoke.kraft.examples.helloworld

import de.peekandpoke.kraft.addons.popups.PopupsManager
import de.peekandpoke.kraft.components.*
import de.peekandpoke.kraft.semanticui.noui
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.*
import org.w3c.dom.pointerevents.PointerEvent

@Suppress("FunctionName")
fun Tag.MainPage() = comp {
    MainPage(it)
}

class MainPage(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        ui.container {
            ui.basic.segment {
                h1 { +"Hello World!" }

                h2 { +"First Counter" }

                CounterComponent(0)

                h2 { +"Another Counter" }

                CounterComponent(10)

                h2 { +"A ticker" }

                TickerComponent(100)

                h2 { +"Component storing data in local storage" }

                LocalStorageComponent("INITIAL")

                h2 { +"Component with a DataLoader" }

                DataLoaderComponent(100)

                ui.divider()

                h2 { +"Left Click | Aux Click | Right Click" }

                ui.card {
                    fun evt2str(evt: PointerEvent) = """
                        MouseEvent:
                            screenX: ${evt.screenX}
                            screenY: ${evt.screenY}
                            clientX: ${evt.clientX}
                            clientY: ${evt.clientY}
                            ctrlKey: ${evt.ctrlKey}
                            shiftKey: ${evt.shiftKey}
                            altKey: ${evt.altKey}
                            metaKey: ${evt.metaKey}
                            button: ${evt.button}
                            buttons: ${evt.buttons}
                            relatedTarget: ${evt.relatedTarget}
                            region: ${evt.region}?
                            pageX: ${evt.pageX}
                            pageY: ${evt.pageY}
                            x: ${evt.x}
                            y: ${evt.y}
                            offsetX: ${evt.offsetX}
                            offsetY: ${evt.offsetY}
                        PointerEvent:
                            pointerId: ${evt.pointerId}
                            width: ${evt.width}
                            height: ${evt.height}
                            pressure: ${evt.pressure}
                            tangentialPressure: ${evt.tangentialPressure}
                            tiltX: ${evt.tiltX}
                            tiltY: ${evt.tiltY}
                            twist: ${evt.twist}
                            pointerType: ${evt.pointerType}
                            isPrimary: ${evt.isPrimary}                        
                    """.trimIndent()

                    onClick {
                        val str = "Left Click:\n\n${evt2str(it)}"
                        console.log(str)
                    }

                    onAuxClick {
                        val str = "Middle Click:\n\n${evt2str(it)}"
                        console.log(str)
                    }

                    onContextMenu {
                        val str = "Right Click:\n\n${evt2str(it)}"
                        console.log(str)
                    }

                    noui.content {
                        +"Click me -> Check console output"
                    }
                }

                ui.divider()

                h2 { +"OnContextMenu" }

                ui.card {
                    noui.content {
                        onContextMenuStoppingEvent {
                            popups.showContextMenu(it) {
                                ui.basic.vertical.menu {
                                    noui.item A { href = "#"; +"Menu 1" }
                                    noui.item A { href = "#"; +"Menu 2" }
                                }
                            }
                        }
                        +"Right click me"
                    }
                }

                ui.divider {}

                h2 { +"Popups" }

                ui.horizontal.list {
                    noui.item {
                        ui.basic.label {
                            onClick {
                                popups.showContentMenu(it, PopupsManager.Positioning.BottomLeft) {
                                    ui.basic.vertical.menu {
                                        noui.item A { href = "#"; +"Menu 1" }
                                        noui.item A { href = "#"; +"Menu 2" }
                                    }
                                }
                            }
                            +"Bottom Left Popup"
                        }
                    }

                    noui.item {
                        ui.basic.label {
                            onClick {
                                popups.showContentMenu(it, PopupsManager.Positioning.BottomRight) {
                                    ui.basic.vertical.menu {
                                        noui.item A { href = "#"; +"Menu 1" }
                                        noui.item A { href = "#"; +"Menu 2" }
                                    }
                                }
                            }
                            +"Bottom Right Popup"
                        }
                    }
                }

                ui.divider {}

                h2 { +"Unsafe Content" }

                style {
                    unsafe {
                        +"""
                            .red-text {
                              color: #FF0000;
                            }
                        """.trimIndent()
                    }
                }

                div("red-text") {
                    +"This text should be red"
                }

                ui.divider {}

                ui.two.column.grid {
                    ui.column {
                        ui.segment {
                            ui.header H2 { +"Playground" }
                        }
                    }
                    ui.column {
                        ui.red.button { +"red" }
                        ui.orange.button { +"orange" }
                        ui.yellow.button { +"yellow" }
                        ui.olive.button { +"olive" }
                        ui.green.button { +"green" }
                        ui.teal.button { +"teal" }
                        ui.blue.button { +"blue" }
                        ui.violet.button { +"violet" }
                        ui.purple.button { +"purple" }
                        ui.pink.button { +"pink" }
                        ui.brown.button { +"brown" }
                        ui.black.button { +"black" }
                        ui.white.button { +"white" }
                    }
                }
            }
        }
    }
}
