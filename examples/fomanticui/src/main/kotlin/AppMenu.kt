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
                noui.header { +"Kraft Howto" }
                noui.menu {
                    noui.item A { href = routes.howtoKraftGettingStarted(); +"Getting started" }
                    noui.item A { href = routes.howtoKraftComponentBasics(); +"Component Basics" }
                    noui.item A { href = routes.howtoKraftComponentState(); +"Component State" }
                }
            }

            noui.item {
                noui.header { +"Forms Howto" }
                noui.menu {
                    noui.item A { href = routes.howtoFormsDemo(); +"Demo" }
                }
            }

            noui.item {
                noui.header { +"Elements" }
                noui.menu {
                    noui.item A { href = routes.elementsButton(); +"Button" }
                    noui.item A { href = routes.elementsContainer(); +"Container" }
                    noui.item A { href = routes.elementsDivider(); +"Divider" }
                    noui.item A { href = routes.elementsFlag(); +"Flag" }
                    noui.item A { href = routes.elementsHeader(); +"Header" }
                    noui.item A { href = routes.elementsIcon(); +"Icon" }
                    noui.item A { href = routes.elementsImage(); +"Image" }
                    noui.item A { href = routes.elementsInput(); +"Input"; ui.red.label { +"TODO" } }
                    noui.item A { href = routes.elementsLabel(); +"Label" }
                    noui.item A { href = routes.elementsList(); +"List" }
                    noui.item A { href = routes.elementsLoader(); +"Loader" }
                    noui.item A { href = routes.elementsPlaceholder(); +"Placeholder" }
                    noui.item A { href = routes.elementsRail(); +"Rail"; ui.red.label { +"TODO" } }
                    noui.item A { href = routes.elementsReveal(); +"Reveal" }
                    noui.item A { href = routes.elementsSegment(); +"Segment"; }
                    noui.item A { href = routes.elementsStep(); +"Step"; ui.red.label { +"TODO" } }
                    noui.item A { href = routes.elementsText(); +"Text" }
                }
            }

            noui.item {
                noui.header { +"Collections" }
                noui.menu {
                    noui.item A { href = routes.collectionsBreadcrumb(); +"Breadcrumb"; ui.red.label { +"TODO" } }
                    noui.item A { href = routes.collectionsForm(); +"Form"; ui.red.label { +"TODO" } }
                    noui.item A { href = routes.collectionsGrid(); +"Grid"; ui.red.label { +"TODO" } }
                    noui.item A { href = routes.collectionsMenu(); +"Menu"; ui.red.label { +"TODO" } }
                    noui.item A { href = routes.collectionsMessage(); +"Message"; ui.red.label { +"TODO" } }
                    noui.item A { href = routes.collectionsTable(); +"Table"; ui.red.label { +"TODO" } }
                }
            }

            noui.item {
                noui.header { +"Views" }
                noui.menu {
                    noui.item A { href = routes.viewsAdvertisement(); +"Advertisement"; ui.red.label { +"TODO" } }
                    noui.item A { href = routes.viewsCard(); +"Card" }
                    noui.item A { href = routes.viewsComment(); +"Comment"; ui.red.label { +"TODO" } }
                    noui.item A { href = routes.viewsFeed(); +"Feed"; ui.red.label { +"TODO" } }
                    noui.item A { href = routes.viewsItem(); +"Item"; ui.red.label { +"TODO" } }
                    noui.item A { href = routes.viewsStatistic(); +"Statistic"; ui.red.label { +"TODO" } }
                }
            }
        }
    }
}
