package de.peekandpoke.kraft.addons.semanticui.forms.old.input

import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.utils.*
import kotlinx.html.InputType
import kotlinx.html.Tag
import kotlin.reflect.KMutableProperty0

// Int Input Field ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Suppress("FunctionName")
fun Tag.InputField(prop: KMutableProperty0<Int>, configure: InputFieldComponent.Config<Int>.() -> Unit = {}) =
    InputField(value = prop.get(), onChange = prop::set, configure)

@Suppress("FunctionName")
fun Tag.InputField(
    value: Int,
    onChange: (Int) -> Unit,
    configure: InputFieldComponent.Config<Int>.() -> Unit = {}
) =
    InputFieldComponent.Config(
        value = value, type = InputType.number, onChange = onChange, toStr = ::numberToString, fromStr = ::stringToInt
    ).apply(configure)
        .let { config -> comp(config.asProps) { ctx -> InputFieldComponent(ctx) } }

// Float Input Field ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Suppress("FunctionName")
fun Tag.InputField(
    prop: KMutableProperty0<Float>,
    configure: InputFieldComponent.Config<Float>.() -> Unit = {}
) =
    InputField(value = prop.get(), onChange = prop::set, configure)

@Suppress("FunctionName")
fun Tag.InputField(
    value: Float,
    onChange: (Float) -> Unit,
    configure: InputFieldComponent.Config<Float>.() -> Unit = {}
) =
    InputFieldComponent.Config(
        value = value, type = InputType.number, onChange = onChange, toStr = ::numberToString, fromStr = ::stringToFloat
    ).apply(configure)
        .let { config -> comp(config.asProps) { ctx -> InputFieldComponent(ctx) } }

// Float Input Field ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Suppress("FunctionName")
fun Tag.InputField(
    prop: KMutableProperty0<Double>,
    configure: InputFieldComponent.Config<Double>.() -> Unit = {}
) =
    InputField(value = prop.get(), onChange = prop::set, configure)

@Suppress("FunctionName")
fun Tag.InputField(
    value: Double,
    onChange: (Double) -> Unit,
    configure: InputFieldComponent.Config<Double>.() -> Unit = {}
) =
    InputFieldComponent.Config(
        value = value,
        type = InputType.number,
        onChange = onChange,
        toStr = ::numberToString,
        fromStr = ::stringToDouble
    ).apply(configure)
        .let { config -> comp(config.asProps) { ctx -> InputFieldComponent(ctx) } }

// String Input Field ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Suppress("FunctionName")
fun Tag.InputField(
    prop: KMutableProperty0<String>,
    configure: InputFieldComponent.Config<String>.() -> Unit = {}
) =
    InputField(value = prop.get(), onChange = prop::set, configure)

@Suppress("FunctionName")
fun Tag.InputField(
    value: String,
    onChange: (String) -> Unit,
    configure: InputFieldComponent.Config<String>.() -> Unit = {}
) =
    InputFieldComponent.Config(
        value = value, type = InputType.text, onChange = onChange, toStr = ::identity, fromStr = ::identity
    ).apply(configure)
        .let { config -> comp(config.asProps) { ctx -> InputFieldComponent(ctx) } }

// Password Input Field ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Suppress("FunctionName")
fun Tag.PasswordField(
    prop: KMutableProperty0<String>,
    configure: InputFieldComponent.Config<String>.() -> Unit = {}
) =
    PasswordField(value = prop.get(), onChange = prop::set, configure)

@Suppress("FunctionName")
fun Tag.PasswordField(
    value: String,
    onChange: (String) -> Unit,
    configure: InputFieldComponent.Config<String>.() -> Unit = {}
) =
    InputFieldComponent.Config(
        value = value, type = InputType.password, onChange = onChange, toStr = ::identity, fromStr = ::identity
    ).apply(configure)
        .let { config -> comp(config.asProps) { ctx -> InputFieldComponent(ctx) } }

// Tags Input Field ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Suppress("FunctionName")
fun Tag.TagsInputField(
    prop: KMutableProperty0<List<String>>,
    configure: InputFieldComponent.Config<List<String>>.() -> Unit = {}
) =
    TagsInputField(value = prop.get(), onChange = prop::set, configure)

@Suppress("FunctionName")
fun Tag.TagsInputField(
    value: List<String>,
    onChange: (List<String>) -> Unit,
    configure: InputFieldComponent.Config<List<String>>.() -> Unit = {}
) =
    InputFieldComponent.Config(
        value = value,
        type = InputType.text,
        onChange = onChange,
        toStr = { input -> input.filter { it.isNotBlank() }.joinToString(" ") },
        fromStr = { input -> input.split(" ").filter { it.isNotBlank() } },
    ).apply(configure)
        .let { config -> comp(config.asProps) { ctx -> InputFieldComponent(ctx) } }

