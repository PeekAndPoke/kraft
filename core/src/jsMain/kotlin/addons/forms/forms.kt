package de.peekandpoke.kraft.addons.forms

import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.Tag

@DslMarker
annotation class FormsDslMarker

fun Component<*>.formController(): FormController {
    return FormController(this)
}

typealias FormFieldTag<T> = Tag.(value: T) -> Unit

fun <T> formField(
    onChange: (T) -> Unit,
    toStr: (T) -> String,
    fromStr: (String) -> T,
    settings: FormField.Settings<T>.() -> Unit,
    render: GenericFormField<T>.(VDom) -> Unit,
): FormFieldTag<T> {

    return { value ->
        comp(
            GenericFormField.Props(
                value = value,
                onChange = onChange,
                toStr = toStr,
                fromStr = fromStr,
                render = render,
                settings = FormField.Settings<T>().apply {
                    settings()
                },
            )
        ) {
            GenericFormField(it)
        }
    }
}
