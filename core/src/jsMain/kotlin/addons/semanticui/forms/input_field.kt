package de.peekandpoke.kraft.addons.semanticui.forms

import addons.forms.Settings
import de.peekandpoke.kraft.addons.forms.FormFieldTag
import de.peekandpoke.kraft.addons.forms.KraftFormsDsl
import de.peekandpoke.kraft.addons.forms.defineFormField
import de.peekandpoke.kraft.utils.*
import de.peekandpoke.ultra.semanticui.SemanticFn
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.InputType
import kotlinx.html.Tag
import kotlinx.html.input

@KraftFormsDsl
fun Tag.uiInputField(
    value: String,
    onChange: (String) -> Unit,
    builder: Settings<String>.() -> Unit,
) {
    val field = uiInputFieldDefinition(
        onChange = onChange,
        builder = builder,
        toStr = ::stringToString,
        fromStr = ::stringToString,
    )

    field(value)
}

@KraftFormsDsl
fun Tag.uiInputField(
    value: String?,
    onChange: (String?) -> Unit,
    builder: Settings<String?>.() -> Unit,
) {
    val field = uiInputFieldDefinition(
        onChange = onChange,
        builder = builder,
        toStr = ::stringToString,
        fromStr = ::stringToString,
    )

    field(value)
}

@KraftFormsDsl
fun Tag.uiInputField(
    value: Int,
    onChange: (Int) -> Unit,
    builder: Settings<Int>.() -> Unit,
) {
    val field = uiInputFieldDefinition(
        onChange = onChange,
        builder = {
            input.type(InputType.number)
            builder()
        },
        toStr = ::numberToString,
        fromStr = ::stringToInt,
    )

    field(value)
}

@KraftFormsDsl
fun Tag.uiInputField(
    value: Int?,
    onChange: (Int?) -> Unit,
    builder: Settings<Int?>.() -> Unit,
) {
    val field = uiInputFieldDefinition<Int?>(
        onChange = onChange,
        builder = {
            input.type(InputType.number)
            builder()
        },
        toStr = ::numberToString,
        fromStr = ::stringToInt,
    )

    field(value)
}

@KraftFormsDsl
fun Tag.uiInputField(
    value: Float,
    onChange: (Float) -> Unit,
    builder: Settings<Float>.() -> Unit,
) {
    val field = uiInputFieldDefinition(
        onChange = onChange,
        builder = {
            input.type(InputType.number)
            builder()
        },
        toStr = ::numberToString,
        fromStr = ::stringToFloat,
    )

    field(value)
}

@KraftFormsDsl
fun Tag.uiInputField(
    value: Float?,
    onChange: (Float?) -> Unit,
    builder: Settings<Float?>.() -> Unit,
) {
    val field = uiInputFieldDefinition<Float?>(
        onChange = onChange,
        builder = {
            input.type(InputType.number)
            builder()
        },
        toStr = ::numberToString,
        fromStr = ::stringToFloat,
    )

    field(value)
}

@KraftFormsDsl
fun Tag.uiInputField(
    value: Double,
    onChange: (Double) -> Unit,
    builder: Settings<Double>.() -> Unit,
) {
    val field = uiInputFieldDefinition(
        onChange = onChange,
        builder = {
            input.type(InputType.number)
            builder()
        },
        toStr = ::numberToString,
        fromStr = ::stringToDouble,
    )

    field(value)
}

@KraftFormsDsl
fun Tag.uiInputField(
    value: Double?,
    onChange: (Double?) -> Unit,
    builder: Settings<Double?>.() -> Unit,
) {
    val field = uiInputFieldDefinition<Double?>(
        onChange = onChange,
        builder = {
            input.type(InputType.number)
            builder()
        },
        toStr = ::numberToString,
        fromStr = ::stringToDouble,
    )

    field(value)
}

@KraftFormsDsl
fun Tag.uiInputField(
    value: Number,
    onChange: (Number) -> Unit,
    builder: Settings<Number>.() -> Unit,
) {
    val field = uiInputFieldDefinition(
        onChange = onChange,
        builder = {
            input.type(InputType.number)
            builder()
        },
        toStr = ::numberToString,
        fromStr = ::stringToNumber,
    )

    field(value)
}

@KraftFormsDsl
fun Tag.uiInputField(
    value: Number?,
    onChange: (Number?) -> Unit,
    builder: Settings<Number?>.() -> Unit,
) {
    val field = uiInputFieldDefinition<Number?>(
        onChange = onChange,
        builder = {
            input.type(InputType.number)
            builder()
        },
        toStr = ::numberToString,
        fromStr = ::stringToNumber,
    )

    field(value)
}

private fun <T> uiInputFieldDefinition(
    onChange: (T) -> Unit,
    builder: Settings<T>.() -> Unit,
    toStr: (T) -> String,
    fromStr: (String) -> T,
): FormFieldTag<T> = defineFormField(onChange, toStr, fromStr, builder) { vdom ->

    with(vdom) {

        val appear: SemanticFn = settings.ui.appear

        ui.appear().given(hasErrors) { error }.field {

            renderLabel()

            input {
                applyAll()
            }

            renderErrors(this)
        }
    }
}
