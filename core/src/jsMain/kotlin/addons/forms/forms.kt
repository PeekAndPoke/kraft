package de.peekandpoke.kraft.addons.forms

import addons.forms.Settings
import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.Tag

@DslMarker
annotation class KraftFormsSettingDsl

@DslMarker
annotation class KraftFormsDsl

@KraftFormsDsl
fun Component<*>.formController(): FormController {
    return FormController(this)
}

typealias FormFieldTag<T> = Tag.(value: T) -> Unit

@KraftFormsDsl
fun <T> defineFormField(
    onChange: (T) -> Unit,
    toStr: (T) -> String,
    fromStr: (String) -> T,
    settings: Settings<T>.() -> Unit,
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
                settings = Settings<T>().apply {
                    settings()
                },
            )
        ) {
            GenericFormField(it)
        }
    }
}
