package de.peekandpoke.kraft.examples.forms

import de.peekandpoke.kraft.addons.forms.formController
import de.peekandpoke.kraft.addons.forms.validation.equalTo
import de.peekandpoke.kraft.addons.semanticui.forms.UiCheckboxField
import de.peekandpoke.kraft.addons.semanticui.forms.semantic
import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.onClick
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.*

@Suppress("FunctionName")
fun Tag.FormWithCheckboxes() = comp {
    FormWithCheckboxes(it)
}

class FormWithCheckboxes(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    data class Obj(val x: String)

    data class State(
        val boolean: Boolean = false,
        val string: String = "yes",
        val obj: Obj = Obj("yes"),
    )

    private var state by value(State())
    private var draft by value(state)

    private fun <P> modify(block: State.(P) -> State): (P) -> Unit = { draft = draft.block(it) }

    private val modifyBoolean = modify<Boolean> { copy(boolean = it) }
    private val modifyString = modify<String> { copy(string = it) }
    private val modifyObj = modify<Obj> { copy(obj = it) }

    private val formCtrl = formController()

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {

        ui.two.column.grid {
            ui.column {
                ui.form {
                    ui.three.fields {
                        UiCheckboxField(state.boolean, modifyBoolean) {
                            label { +State::boolean.name }
                            accepts(equalTo(true, "Please check"))
                        }

                        UiCheckboxField(state.string, "no" to "yes", modifyString) {
                            label { +State::string.name }
                            semantic.checkbox.toggle()
                        }

                        UiCheckboxField(state.obj, Obj("no") to Obj("yes"), modifyObj) {
                            label { +State::obj.name }
                            semantic.checkbox.slider()
                        }
                    }
                }

                ui.divider {}

                val canSubmit = formCtrl.isValid && draft != state

                ui.button.given(!canSubmit) { disabled }.then {
                    +"Submit"

                    onClick {
                        if (formCtrl.validate() && canSubmit) {
                            state = draft
                        }
                    }
                }
            }

            ui.column {
                renderStateAndDraftTable(
                    state,
                    draft,
                    listOf(
                        State::boolean { it.toString() },
                        State::string { it },
                        State::obj { it.toString() },
                    )
                )
            }
        }
    }
}
