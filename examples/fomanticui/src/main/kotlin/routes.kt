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
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.input.InputPage
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.label.LabelPage
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.list.ListPage
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.loader.LoaderPage
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.placeholder.PlaceholderPage
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.rail.RailPage
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.reveal.RevealPage
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.segment.SegmentPage
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.step.StepPage
import de.peekandpoke.kraft.examples.fomanticui.pages.elements.text.TextPage
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
    val elementsInput = Static("/elements/input")
    val elementsLabel = Static("/elements/label")
    val elementsList = Static("/elements/list")
    val elementsLoader = Static("/elements/loader")
    val elementsPlaceholder = Static("/elements/placeholder")
    val elementsRail = Static("/elements/rail")
    val elementsReveal = Static("/elements/reveal")
    val elementsSegment = Static("/elements/segment")
    val elementsStep = Static("/elements/step")
    val elementsText = Static("/elements/text")

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
    mount(routes.elementsInput) { InputPage() }
    mount(routes.elementsLabel) { LabelPage() }
    mount(routes.elementsList) { ListPage() }
    mount(routes.elementsLoader) { LoaderPage() }
    mount(routes.elementsPlaceholder) { PlaceholderPage() }
    mount(routes.elementsRail) { RailPage() }
    mount(routes.elementsReveal) { RevealPage() }
    mount(routes.elementsSegment) { SegmentPage() }
    mount(routes.elementsStep) { StepPage() }
    mount(routes.elementsText) { TextPage() }

    mount(routes.formDemos) { FormDemosPage() }

    mount(routes.viewsCard) { CardPage() }
}
