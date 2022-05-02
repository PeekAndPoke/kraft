package de.peekandpoke.kraft.examples.forms

import de.peekandpoke.kraft.addons.forms.formController
import de.peekandpoke.kraft.addons.forms.validation.equalTo
import de.peekandpoke.kraft.addons.semanticui.forms.UiPasswordField
import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.onClick
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.Tag


@Suppress("FunctionName")
fun Tag.FormWithPasswords() = comp {
    FormWithPasswords(it)
}

class FormWithPasswords(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    data class State(
        val password: String = "",
        val repeat: String = "",
    )

    private var state by value(State())
    private var draft by value(state)

    private val formCtrl = formController()

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {

        ui.two.column.grid {
            ui.column {
                ui.form {
                    ui.three.fields {
                        UiPasswordField(draft.password, { draft = draft.copy(password = it) }) {
                            label { +"Password" }
                        }

                        UiPasswordField(draft.repeat, { draft = draft.copy(repeat = it) }) {
                            label { +"Repeat" }

                            accepts(equalTo({ draft.password }, "Passwords must match"))
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
                        State::password { it },
                        State::repeat { it },
                    )
                )
            }
        }
    }
}
