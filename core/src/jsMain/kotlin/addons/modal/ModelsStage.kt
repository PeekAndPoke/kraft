package de.peekandpoke.kraft.addons.modal

import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.components.Ctx
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.Tag
import kotlinx.html.div

@Suppress("FunctionName")
fun Tag.ModelsStage(
    modals: ModalsManager
) = comp(
    ModelsStage.Props(modals = modals)
) {
    ModelsStage(it)
}

class ModelsStage(ctx: Ctx<Props>) : Component<ModelsStage.Props>(ctx) {

    ////  PROPS  //////////////////////////////////////////////////////////////////////////////////////////////////

    data class Props(
        val modals: ModalsManager,
    )

    ////  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    private val current: List<ModalsManager.Handle> by subscribingTo(props.modals.current)

    ////  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        div(classes = "modal-stage") {
//            key = current.hashCode().toString()

//            console.log("ModelDialogStage", current.hashCode().toString(), current.size)

            current.forEach {
                it.view(this, it)
            }
        }
    }
}
