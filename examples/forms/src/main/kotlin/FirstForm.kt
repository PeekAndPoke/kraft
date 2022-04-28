package de.peekandpoke.kraft.examples.forms

import de.peekandpoke.kraft.addons.forms.formController
import de.peekandpoke.kraft.addons.semanticui.forms.input.InputField
import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.Tag

@Suppress("FunctionName")
fun Tag.FirstForm() = comp {
    FirstForm(it)
}

class FirstForm(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    private var input by value("")

    private val formCtrl = formController()

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {

        ui.segment {
            ui.form {
                InputField(::input)
            }
        }
    }
}
