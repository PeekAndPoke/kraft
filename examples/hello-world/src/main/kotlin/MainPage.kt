package de.peekandpoke.kraft.examples.helloworld

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.onContextMenuStoppingEvent
import de.peekandpoke.kraft.semanticui.noui
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.Tag
import kotlinx.html.h1
import kotlinx.html.h2

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

                h2 { +"OnContextMenu" }

                ui.card {
                    noui.content {
                        onContextMenuStoppingEvent {
                            popups.showContextMenu(it) {
                                ui.basic.vertical.menu {
                                    noui.item A {
                                        href = "#"
                                        +"Menu 1"
                                    }
                                    noui.item A {
                                        href = "#"
                                        +"Menu 2"
                                    }
                                }
                            }
                        }
                        +"Right click me"
                    }
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
