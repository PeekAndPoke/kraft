package de.peekandpoke.kraft.examples.helloworld

import de.peekandpoke.kraft.addons.popups.PopupsManager
import de.peekandpoke.kraft.addons.popups.PopupsStage
import de.peekandpoke.kraft.components.onContextMenuStoppingEvent
import de.peekandpoke.kraft.semanticui.noui
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.preact.PreactVDomEngine
import kotlinx.browser.document
import kotlinx.html.h1
import kotlinx.html.h2
import org.w3c.dom.HTMLElement

fun main() {
    val mountPoint = document.getElementById("spa") as HTMLElement

    val popups = PopupsManager()

    PreactVDomEngine(mountPoint) {
        PopupsStage(popups)

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

        ui.container {
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
