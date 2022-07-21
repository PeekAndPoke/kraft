package de.peekandpoke.kraft.examples.fomanticui

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.semanticui.noui
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.Tag
import kotlinx.html.a
import kotlinx.html.b
import kotlinx.html.img

@Suppress("FunctionName")
fun Tag.AppMenu() = comp {
    AppMenu(it)
}

class AppMenu(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        ui.vertical.inverted.menu {
            noui.item {
                ui.with("logo").icon.image A {
                    href = routes.homeSlash()
                    img(src = "https://fomantic-ui.com/images/logo.png")
                }

                a(href = routes.homeSlash()) {
                    b { +"FomanticUI + KRAFT" }
                }
            }

            noui.item {
                noui.header { +"Elements" }
                noui.menu {
                    noui.item A {
                        href = routes.elementsButton()
                        +"Button"
                    }
                    noui.item A {
                        href = routes.elementsDivider()
                        +"Divider"
                        ui.tiny.red.label { +"Todo" }
                    }
                    noui.item A {
                        href = routes.elementsHeader()
                        +"Header"
                        ui.tiny.red.label { +"Todo" }
                    }
                    noui.item A {
                        href = routes.elementsIcon()
                        +"Icon"
                    }
                }
            }

            noui.item {
                noui.header { +"Forms" }
                noui.menu {
                    noui.item A {
                        href = routes.formDemos()
                        +"Demos"
                    }
                }
            }

            noui.item {
                noui.header { +"Views" }
                noui.menu {
                    noui.item A {
                        href = routes.viewsCard()
                        +"Card"
                        ui.tiny.red.label { +"Todo" }
                    }
                }
            }
        }
    }
}
