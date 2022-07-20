package de.peekandpoke.kraft.examples.fomanticui

import de.peekandpoke.kraft.addons.routing.Router
import de.peekandpoke.kraft.addons.routing.router
import de.peekandpoke.kraft.vdom.preact.PreactVDomEngine
import kotlinx.browser.document
import org.w3c.dom.HTMLElement

val routes = Routes()

val router: Router = router {
    mount(routes)
}

fun main() {
    val mountPoint = document.getElementById("spa") as HTMLElement

    PreactVDomEngine(mountPoint) {
        App()
    }

    router.current.subscribeToStream {
        console.log("Current route: ${it.route} | ${it.matchedRoute.route.pattern}")
    }

    router.resolveCurrentRoute()
}
