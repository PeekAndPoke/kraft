package de.peekandpoke.kraft.examples.forms

import de.peekandpoke.kraft.addons.forms.formController
import de.peekandpoke.kraft.addons.semanticui.forms.UiDateField
import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.onClick
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.common.datetime.MpInstant
import de.peekandpoke.ultra.common.datetime.MpLocalDate
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.*


@Suppress("FunctionName")
fun Tag.FormWithDatesAndTimes() = comp {
    FormWithDatesAndTimes(it)
}

class FormWithDatesAndTimes(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    data class State(
        val date: MpLocalDate = MpInstant.now().atSystemDefaultZone().toLocalDate(),
    )

    private var state by value(State())
    private var draft by value(state)

    private fun <P> modify(block: State.(P) -> State): (P) -> Unit = { draft = draft.block(it) }

    private val modifyDate = modify<MpLocalDate> { copy(date = it) }

    private val formCtrl = formController()

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {

        ui.two.column.grid {
            ui.column {
                ui.form {
                    UiDateField(state.date, modifyDate) {
                        label { +"Date Input" }
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

        ui.striped.celled.table Table {
            thead {
                tr {
                    th { +"Field" }
                    th { +"State" }
                    th { +"Draft" }
                }
            }
            tbody {
                tr {
                    td { +"input" }
                    td { +state.date.toIsoString() }
                    td { +draft.date.toIsoString() }
                }
            }
        }
    }
}
