package de.peekandpoke.kraft.examples.fomanticui

import de.peekandpoke.kraft.Kraft
import de.peekandpoke.kraft.addons.routing.Router
import de.peekandpoke.kraft.addons.routing.router
import de.peekandpoke.kraft.vdom.preact.PreactVDomEngine
import kotlinx.browser.document
import org.w3c.dom.HTMLElement

/** Initializes KRAFT */
val kraft = Kraft.initialize()

/** Create the routes */
val routes = Routes()

/** Create the router */
val router: Router = router { mount(routes) }

fun main() {
    val mountPoint = document.getElementById("spa") as HTMLElement

    PreactVDomEngine(mountPoint) {
        App()
    }

    router.resolveCurrentRoute()
}
