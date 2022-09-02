package de.peekandpoke.kraft.examples.jsaddons

import de.peekandpoke.kraft.addons.routing.RouterBuilder
import de.peekandpoke.kraft.addons.routing.Static
import de.peekandpoke.kraft.examples.jsaddons.chartjs.ChartJsExample
import de.peekandpoke.kraft.examples.jsaddons.jwtdecode.JwtDecodeExample
import de.peekandpoke.kraft.examples.jsaddons.konva.KonvaExample
import de.peekandpoke.kraft.examples.jsaddons.marked.MarkedExample
import de.peekandpoke.kraft.examples.jsaddons.prismjs.PrismJsExample
import de.peekandpoke.kraft.examples.jsaddons.signaturepad.SignaturePadExample

class Routes {

    val home = Static("")
    val homeSlash = Static("/")

    val chartJs = Static("/example/chart-js")
    val jwtDecode = Static("/example/jwt-decode")
    val konva = Static("/example/konva")
    val marked = Static("/example/marked")
    val prismjs = Static("/example/prism-js")
    val signaturePad = Static("/example/signature-pad")
}

fun RouterBuilder.mount(routes: Routes) {
    mount(routes.home) { HomePage() }
    mount(routes.homeSlash) { HomePage() }

    mount(routes.chartJs) { ChartJsExample() }
    mount(routes.jwtDecode) { JwtDecodeExample() }
    mount(routes.konva) { KonvaExample() }
    mount(routes.marked) { MarkedExample() }
    mount(routes.prismjs) { PrismJsExample() }
    mount(routes.signaturePad) { SignaturePadExample() }
}
