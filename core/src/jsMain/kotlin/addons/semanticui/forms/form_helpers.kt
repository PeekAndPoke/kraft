package de.peekandpoke.kraft.addons.semanticui.forms

import de.peekandpoke.kraft.addons.forms.FormFieldComponent
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.FlowContent

fun <T, P : FormFieldComponent.Props<T>> renderErrors(field: FormFieldComponent<T, P>, flow: FlowContent) {
    flow.apply {
        if (field.touched) {
            field.errors.filter { it.isNotBlank() }.forEach { error ->
                ui.basic.red.pointing.label { +error }
            }
        }
    }
}
