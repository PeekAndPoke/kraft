package de.peekandpoke.kraft.addons.routing

import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.components.Ctx
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.key
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.Tag
import kotlinx.html.div

/**
 * The router component displays the view that is associated with the [ActiveRoute] of the given [Router]
 */
@Suppress("FunctionName")
fun Tag.RouterComponent(router: Router) = comp(RouterComponent.Props(router)) {
    RouterComponent(it)
}

class RouterComponent internal constructor(ctx: Ctx<Props>) : Component<RouterComponent.Props>(ctx) {

    data class Props(
        val router: Router
    )

    ////  STATE  ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private val current: ActiveRoute by subscribingTo(props.router.current)

    ////  IMPL  ////////////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {

        div(classes = "router") {
            // We define a key, so that the VDomEngine does a full redraw of the content, when the matched route changes
            key = current.uri.split("?").firstOrNull() ?: ""

            current.render(this)
        }
    }
}
