package de.peekandpoke.kraft.examples.forms

import de.peekandpoke.kraft.addons.forms.formController
import de.peekandpoke.kraft.addons.forms.validation.numbers.greaterThan
import de.peekandpoke.kraft.addons.forms.validation.numbers.lessThan
import de.peekandpoke.kraft.addons.semanticui.forms.UiInputField
import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.onClick
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.*


@Suppress("FunctionName")
fun Tag.FormWithNullablePrimitives() = comp {
    FormWithNullablePrimitives(it)
}

class FormWithNullablePrimitives(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    data class State(
        val textInput: String? = null,
        val intInput: Int? = null,
        val floatInput: Float? = null,
        val doubleInput: Double? = null,
        val numberInput: Number? = null,
    )

    private var state by value(State())
    private var draft by value(state)

    private fun <P> modify(block: State.(P) -> State): (P) -> Unit = { draft = draft.block(it) }

    private val modifyString = modify<String?> { copy(textInput = it) }
    private val modifyInt = modify<Int?> { copy(intInput = it) }
    private val modifyFloat = modify<Float?> { copy(floatInput = it) }
    private val modifyDouble = modify<Double?> { copy(doubleInput = it) }
    private val modifyNumber = modify<Number?> { copy(numberInput = it) }

    private val formCtrl = formController()

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {

        ui.two.column.grid {
            ui.column {
                ui.form {
                    ui.three.fields {

                        UiInputField(state.textInput, modifyString) {
                            label { +"Text Input" }
                            placeholder("Enter some text")
                        }

                        UiInputField(state.intInput, modifyInt) {
                            label { +"Int Input" }
                            placeholder("Enter a number")
                            input.step(3)

                            accepts(
                                greaterThan(6.0),
                                lessThan(15.0)
                            )
                        }

                        UiInputField(state.floatInput, modifyFloat) {
                            label { +"Float Input" }
                            placeholder("Enter a number")

                            accepts(
                                greaterThan(5.0),
                                lessThan(20.0)
                            )
                        }
                    }

                    ui.three.fields {
                        UiInputField(state.doubleInput, modifyDouble) {
                            label { +"Double Input" }
                            placeholder("Enter a number")
                            input.step(0.5)

                            accepts(
                                greaterThan(3.0),
                                lessThan(10.0)
                            )
                        }

                        UiInputField(state.numberInput, modifyNumber) {
                            label { +"Number Input" }
                            placeholder("Enter a number")
                            input.step(0.5)
                            accepts(
                                greaterThan(3.0),
                                lessThan(10.0)
                            )
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
                renderDataTable()
            }
        }
    }

    private fun FlowContent.renderDataTable() {

        renderStateAndDraftTable(
            state,
            draft,
            listOf(
                State::textInput { it.toString() },
                State::intInput { it.toString() },
                State::floatInput { it.toString() },
                State::doubleInput { it.toString() },
                State::numberInput { it.toString() },
            )
        )
    }
}
