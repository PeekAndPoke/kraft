package de.peekandpoke.kraft.addons.popups

import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.components.Ctx
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.semanticui.css
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.css.Position
import kotlinx.css.position
import kotlinx.html.Tag
import kotlinx.html.div

@Suppress("FunctionName")
fun Tag.PopupsStage(
    popups: PopupsManager
) = comp(
    PopupsStage.Props(popups = popups)
) {
    PopupsStage(it)
}

class PopupsStage(ctx: Ctx<Props>) : Component<PopupsStage.Props>(ctx) {

    ////  PROPS  //////////////////////////////////////////////////////////////////////////////////////////////////

    data class Props(
        val popups: PopupsManager,
    )

    ////  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    private val current: List<PopupsManager.Handle> by subscribingTo(props.popups.current)

    ////  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        div(classes = "popup-stage") {
            css {
                position = Position.absolute
            }
//            key = current.hashCode().toString()

//            console.log("ModelDialogStage", current.hashCode().toString(), current.size)

            current.forEach {
                it.view(this, it)
            }
        }
    }
}
