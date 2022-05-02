package de.peekandpoke.kraft.examples.forms

import de.peekandpoke.kraft.addons.forms.formController
import de.peekandpoke.kraft.addons.semanticui.forms.UiDateField
import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.onClick
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.common.datetime.*
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.*


@Suppress("FunctionName")
fun Tag.FormWithDates() = comp {
    FormWithDates(it)
}

class FormWithDates(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    data class State(
        val localDate: MpLocalDate = Kronos.systemUtc.localDateTimeNow().toDate(),
        val localDateTime: MpLocalDateTime = Kronos.systemUtc.localDateTimeNow(),
        val zonedDateTime: MpZonedDateTime = Kronos.systemUtc.zonedDateTimeNow(MpTimezone.of("Europe/Berlin")),
    )

    private var state by value(State())
    private var draft by value(state)

    private fun <P> modify(block: State.(P) -> State): (P) -> Unit = { draft = draft.block(it) }

    private val modifyLocalDate = modify<MpLocalDate> { copy(localDate = it) }
    private val modifyLocalDateTime = modify<MpLocalDateTime> { copy(localDateTime = it) }
    private val modifyZonedDateTime = modify<MpZonedDateTime> { copy(zonedDateTime = it) }

    private val formCtrl = formController()

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {

        ui.two.column.grid {
            ui.column {
                ui.form {
                    ui.three.fields {
                        UiDateField(state.localDate, modifyLocalDate) {
                            label { +State::localDate.name }
                        }

                        UiDateField(state.localDateTime, modifyLocalDateTime) {
                            label { +State::localDateTime.name }
                        }

                        UiDateField(state.zonedDateTime, modifyZonedDateTime) {
                            label { +State::zonedDateTime.name }
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
                        State::localDate { it.toIsoString() },
                        State::localDateTime { it.toIsoString() },
                        State::zonedDateTime { it.toIsoString() },
                    )
                )
            }
        }
    }
}
