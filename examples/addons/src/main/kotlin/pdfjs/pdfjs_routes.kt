package de.peekandpoke.kraft.examples.jsaddons.pdfjs

import de.peekandpoke.kraft.addons.routing.RouterBuilder
import de.peekandpoke.kraft.addons.routing.Static

class PdfJsRoutes {
    val index = Static("/example/pdfjs")
    val paged = Static("/example/pdfjs/paged")
    val scrolling = Static("/example/pdfjs/scrolling")
}

fun RouterBuilder.mount(routes: PdfJsRoutes) {
    mount(routes.index) { PdfJsExample() }
    mount(routes.paged) { PdfJsPagedViewerExample() }
    mount(routes.scrolling) { PdfJsScrollingViewerExample() }
}
