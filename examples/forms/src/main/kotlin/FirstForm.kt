package de.peekandpoke.kraft.examples.forms

import de.peekandpoke.kraft.addons.forms.FormField
import de.peekandpoke.kraft.addons.forms.FormField.Settings.Companion.accepts
import de.peekandpoke.kraft.addons.forms.FormField.Settings.Companion.inputStep
import de.peekandpoke.kraft.addons.forms.FormField.Settings.Companion.inputType
import de.peekandpoke.kraft.addons.forms.FormField.Settings.Companion.label
import de.peekandpoke.kraft.addons.forms.FormField.Settings.Companion.placeholder
import de.peekandpoke.kraft.addons.forms.FormFieldTag
import de.peekandpoke.kraft.addons.forms.formController
import de.peekandpoke.kraft.addons.forms.formField
import de.peekandpoke.kraft.addons.forms.validation.strings.notBlank
import de.peekandpoke.kraft.addons.semanticui.forms.renderErrors
import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.onClick
import de.peekandpoke.kraft.utils.identity
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.semanticui.icon
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.InputType
import kotlinx.html.Tag
import kotlinx.html.input


fun <T> inputField(
    onChange: (T) -> Unit,
    toStr: (T) -> String,
    fromStr: (String) -> T,
    builder: FormField.Settings<T>.() -> Unit,
): FormFieldTag<T> = formField(onChange, toStr, fromStr, builder) { vdom ->

    with(vdom) {
        ui.given(hasErrors) { error }.field {

            renderLabel()

            input {
                applyAll()
            }

            renderErrors(this)
        }
    }
}

fun Tag.inputField(
    value: String,
    onChange: (String) -> Unit,
    builder: FormField.Settings<String>.() -> Unit,
) {
    val field = inputField(
        onChange = onChange,
        toStr = ::identity,
        fromStr = ::identity,
        builder = builder,
    )

    field(value)
}


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
                inputField(input, { input = it }) {

                    inputType(InputType.number)

                    inputStep(0.1)

                    label {
                        +"Test Input"
                        icon.pen()
                    }
                    placeholder("Enter some text")

                    accepts(notBlank())
                }
            }

            ui.divider {}

            ui.button.given(formCtrl.isNotValid) { disabled }.then {
                +"Submit"

                onClick {
                    if (formCtrl.validate()) {
                        // TODO: some feedback
                    }
                }
            }
        }
    }
}
