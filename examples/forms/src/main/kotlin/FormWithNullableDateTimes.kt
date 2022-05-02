package de.peekandpoke.kraft.examples.forms

import de.peekandpoke.kraft.addons.forms.formController
import de.peekandpoke.kraft.addons.forms.validation.nonNull
import de.peekandpoke.kraft.addons.semanticui.forms.UiDateTimeField
import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.onClick
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.common.datetime.MpLocalDateTime
import de.peekandpoke.ultra.common.datetime.MpTimezone
import de.peekandpoke.ultra.common.datetime.MpZonedDateTime
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.*


@Suppress("FunctionName")
fun Tag.FormWithNullableDateTimes() = comp {
    FormWithNullableDateTimes(it)
}

class FormWithNullableDateTimes(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    data class State(
        val localDateTime: MpLocalDateTime? = null,
        val zonedDateTime: MpZonedDateTime? = null,
    )

    private var state by value(State())
    private var draft by value(state)

    private fun <P> modify(block: State.(P) -> State): (P) -> Unit = { draft = draft.block(it) }

    private val modifyLocalDateTime = modify<MpLocalDateTime?> { copy(localDateTime = it) }
    private val modifyZonedDateTime = modify<MpZonedDateTime?> { copy(zonedDateTime = it) }

    private val formCtrl = formController()

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {

        ui.two.column.grid {
            ui.column {
                ui.form {
                    ui.three.fields {
                        UiDateTimeField(state.localDateTime, modifyLocalDateTime) {
                            label { +State::localDateTime.name }
                            accepts(nonNull())
                        }

                        UiDateTimeField(state.zonedDateTime, MpTimezone.of("Europe/Berlin"), modifyZonedDateTime) {
                            label { +State::zonedDateTime.name }
                            accepts(nonNull())
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
                        State::localDateTime { it?.toIsoString() },
                        State::zonedDateTime { it?.toIsoString() },
                    )
                )
            }
        }
    }
}
