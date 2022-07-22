package de.peekandpoke.kraft.examples.fomanticui

import de.peekandpoke.kraft.addons.routing.RouterBuilder
import de.peekandpoke.kraft.addons.routing.Static
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.button.ButtonPage
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.container.ContainerPage
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.divider.DividerPage
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.flag.FlagPage
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.header.HeaderPage
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.icon.IconPage
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.image.ImagePage
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.label.LabelPage
import de.peekandpoke.kraft.examples.fomanticui.pages.forms.demo.FormDemosPage
import de.peekandpoke.kraft.examples.fomanticui.pages.home.HomePage
import de.peekandpoke.kraft.examples.fomanticui.pages.views.card.CardPage

class Routes {
    val home = Static("")
    val homeSlash = Static("/")

    val elementsButton = Static("/elements/button")
    val elementsContainer = Static("/elements/container")
    val elementsDivider = Static("/elements/divider")
    val elementsFlag = Static("/elements/flag")
    val elementsHeader = Static("/elements/header")
    val elementsIcon = Static("/elements/icon")
    val elementsImage = Static("/elements/image")
    val elementsLabel = Static("/elements/label")

    val formDemos = Static("/form/demos")

    val viewsCard = Static("/views/card")
}

fun RouterBuilder.mount(routes: Routes) {
    mount(routes.home) { HomePage() }
    mount(routes.homeSlash) { HomePage() }

    mount(routes.elementsButton) { ButtonPage() }
    mount(routes.elementsContainer) { ContainerPage() }
    mount(routes.elementsDivider) { DividerPage() }
    mount(routes.elementsFlag) { FlagPage() }
    mount(routes.elementsHeader) { HeaderPage() }
    mount(routes.elementsIcon) { IconPage() }
    mount(routes.elementsImage) { ImagePage() }
    mount(routes.elementsLabel) { LabelPage() }

    mount(routes.formDemos) { FormDemosPage() }

    mount(routes.viewsCard) { CardPage() }
}
