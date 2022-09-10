package de.peekandpoke.kraft.addons.semanticui.forms

import de.peekandpoke.kraft.addons.forms.*
import de.peekandpoke.kraft.addons.semanticui.forms.UiInputFieldComponent.Options
import de.peekandpoke.kraft.components.Ctx
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.key
import de.peekandpoke.kraft.components.onInput
import de.peekandpoke.kraft.messages.sendMessage
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.utils.*
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.common.datetime.*
import kotlinx.html.*
import org.w3c.dom.HTMLInputElement
import kotlin.reflect.KMutableProperty0

@KraftFormsDsl
val Tag.UiInputField get() = UiInputFieldRenderer(this)

@KraftFormsDsl
val Tag.UiPasswordField get() = UiPasswordFieldRenderer(this)

@KraftFormsDsl
val Tag.UiDateField get() = UiDateFieldRenderer(this)

@KraftFormsDsl
val Tag.UiDateTimeField get() = UiDateTimeFieldRenderer(this)

@KraftFormsDsl
val Tag.UiTimeField get() = UiTimeFieldRenderer(this)

@Suppress("FunctionName")
fun <T> Tag.UiInputField(
    value: T,
    onChange: (T) -> Unit,
    toStr: (T) -> String,
    fromStr: (String) -> T,
    builder: Options<T>.() -> Unit = {},
) = comp(
    UiInputFieldComponent.Props(
        value = value,
        onChange = onChange,
        toStr = toStr,
        fromStr = fromStr,
        options = Options<T>().apply(builder),
    )
) {
    UiInputFieldComponent(it)
}

class UiInputFieldComponent<T, P : UiInputFieldComponent.Props<T>>(ctx: Ctx<P>) :
    GenericFormField<T, Options<T>, P>(ctx) {

    class Options<T> : FieldOptions.Base<T>(), SemanticOptions<T>, SemanticOptions.Input<T>

    data class Props<X>(
        override val value: X,
        override val onChange: (X) -> Unit,
        override val options: Options<X>,
        val toStr: (X) -> String,
        val fromStr: (String) -> X,
    ) : GenericFormField.Props<X, Options<X>>

    internal var typeOverride: InputType? by value(null)

    internal val effectiveType: InputType? get() = typeOverride ?: options.type()

    init {
        lifecycle {
            onMount {
                options.autofocusValue()?.takeIf { it }?.let {
                    focus("input")
                }
            }
        }
    }


    override fun VDom.render() {
        ui.with(options.appear.getOrDefault { this }).given(hasErrors) { error }.field {
            key = domKey

            renderLabel("input")

            val wrap = options.wrapFieldWith()

            when {
                wrap != null -> ui.wrap().then {
                    options.renderBeforeField()?.let { it(this, this@UiInputFieldComponent) }

                    renderField()

                    options.renderAfterField()?.let { it(this, this@UiInputFieldComponent) }
                }

                else -> renderField()
            }

            renderErrors(this)
        }
    }

    private fun FlowContent.renderField() {
        input {
            applyAll()
        }
    }

    fun setInput(input: String) {
        try {
            val newValue = props.fromStr(input)
            setValue(newValue)

        } catch (t: Throwable) {
            console.error(t)

            // TODO: how to translate this?
            errors = listOf("Invalid value")
        }

        sendMessage(FormFieldInputChanged(this))
    }

    private fun valueAsString(): String {
        return props.toStr(currentValue)
    }

    fun INPUT.applyAll() {
        setValue()
        track()

        applyFormatValue()
        applyName()
        applyPlaceholder()
        applyStep()
        applyType()
    }

    private fun INPUT.track() {
        onInput {
            setInput((it.target as HTMLInputElement).value)
        }
    }

    private fun INPUT.setValue() {
        value = valueAsString()
    }

    private fun INPUT.applyFormatValue() {
        options.formatValue()?.let { attributes["format-value"] = it }
    }

    private fun INPUT.applyPlaceholder() {
        options.placeholder()?.takeIf { it.isNotBlank() }?.let { placeholder = it }
    }

    private fun INPUT.applyName() {
        options.name()?.let { name = it }
    }

    private fun INPUT.applyStep() {
        options.step()?.let { step = it.toString() }
    }

    private fun INPUT.applyType() {
        effectiveType?.let { type = it }
    }
}

class UiInputFieldRenderer(private val tag: Tag) {
    /**
     * Renders the field for a String
     */
    @KraftFormsDsl
    operator fun invoke(
        prop: KMutableProperty0<String>,
        builder: Options<String>.() -> Unit = {},
    ) = invoke(prop(), prop::set, builder)

    /**
     * Renders the field for a String
     */
    @KraftFormsDsl
    operator fun invoke(
        value: String,
        onChange: (String) -> Unit,
        builder: Options<String>.() -> Unit = {},
    ) = tag.UiInputField(value, onChange, ::stringToString, ::stringToString, builder)

    /**
     * Renders the field for a nullable String
     */
    @KraftFormsDsl
    fun nullable(
        prop: KMutableProperty0<String?>,
        builder: Options<String?>.() -> Unit = {},
    ) = nullable(prop(), prop::set, builder)

    /**
     * Renders the field for a nullable String
     */
    @KraftFormsDsl
    fun nullable(
        value: String?,
        onChange: (String?) -> Unit,
        builder: Options<String?>.() -> Unit = {},
    ) = tag.UiInputField(value, onChange, ::stringToString, ::stringToString, builder)

    /**
     * Renders the field for an Int
     */
    @KraftFormsDsl
    operator fun invoke(
        prop: KMutableProperty0<Int>,
        builder: Options<Int>.() -> Unit = {},
    ) = invoke(prop(), prop::set, builder)

    /**
     * Renders the field for an Int
     */
    @KraftFormsDsl
    operator fun invoke(
        value: Int,
        onChange: (Int) -> Unit,
        builder: Options<Int>.() -> Unit = {},
    ) = tag.UiInputField(value, onChange, ::numberToString, ::stringToInt) {
        type(InputType.number)
        builder()
    }

    /**
     * Renders the field for a nullable Int
     */
    @KraftFormsDsl
    fun nullable(
        prop: KMutableProperty0<Int?>,
        builder: Options<Int?>.() -> Unit = {},
    ) = nullable(prop(), prop::set, builder)

    /**
     * Renders the field for a nullable Int
     */
    @KraftFormsDsl
    fun nullable(
        value: Int?,
        onChange: (Int?) -> Unit,
        builder: Options<Int?>.() -> Unit = {},
    ) = tag.UiInputField(value, onChange, ::numberToString, ::stringToInt) {
        type(InputType.number)
        builder()
    }

    /**
     * Renders the field for a Float
     */
    @KraftFormsDsl
    operator fun invoke(
        prop: KMutableProperty0<Float>,
        builder: Options<Float>.() -> Unit = {},
    ) = invoke(prop(), prop::set, builder)

    /**
     * Renders the field for a Float
     */
    @KraftFormsDsl
    operator fun invoke(
        value: Float,
        onChange: (Float) -> Unit,
        builder: Options<Float>.() -> Unit = {},
    ) = tag.UiInputField(value, onChange, ::numberToString, ::stringToFloat) {
        type(InputType.number)
        builder()
    }

    /**
     * Renders the field for a nullable Float
     */
    @KraftFormsDsl
    fun nullable(
        prop: KMutableProperty0<Float?>,
        builder: Options<Float?>.() -> Unit = {},
    ) = nullable(prop(), prop::set, builder)

    /**
     * Renders the field for a nullable Float
     */
    @KraftFormsDsl
    fun nullable(
        value: Float?,
        onChange: (Float?) -> Unit,
        builder: Options<Float?>.() -> Unit = {},
    ) = tag.UiInputField(value, onChange, ::numberToString, ::stringToFloat) {
        type(InputType.number)
        builder()
    }

    /**
     * Renders the field for a Double
     */
    @KraftFormsDsl
    operator fun invoke(
        prop: KMutableProperty0<Double>,
        builder: Options<Double>.() -> Unit = {},
    ) = invoke(prop(), prop::set, builder)

    /**
     * Renders the field for a Double
     */
    @KraftFormsDsl
    operator fun invoke(
        value: Double,
        onChange: (Double) -> Unit,
        builder: Options<Double>.() -> Unit = {},
    ) = tag.UiInputField(value, onChange, ::numberToString, ::stringToDouble) {
        type(InputType.number)
        builder()
    }

    /**
     * Renders the field for a nullable Double
     */
    @KraftFormsDsl
    fun nullable(
        prop: KMutableProperty0<Double?>,
        builder: Options<Double?>.() -> Unit = {},
    ) = nullable(prop(), prop::set, builder)

    /**
     * Renders the field for a nullable Double
     */
    @KraftFormsDsl
    fun nullable(
        value: Double?,
        onChange: (Double?) -> Unit,
        builder: Options<Double?>.() -> Unit = {},
    ) = tag.UiInputField(value, onChange, ::numberToString, ::stringToDouble) {
        type(InputType.number)
        builder()
    }
}

class UiPasswordFieldRenderer(private val tag: Tag) {
    /**
     * Renders the field for a String
     */
    @KraftFormsDsl
    operator fun invoke(
        prop: KMutableProperty0<String>,
        builder: Options<String>.() -> Unit = {},
    ) = invoke(prop(), prop::set, builder)

    /**
     * Renders the field for a String
     */
    @KraftFormsDsl
    operator fun invoke(
        value: String,
        onChange: (String) -> Unit,
        builder: Options<String>.() -> Unit = {},
    ) = tag.UiInputField(value, onChange, ::stringToString, ::stringToString) {
        type(InputType.password)
        builder()
    }
}

class UiDateFieldRenderer(private val tag: Tag) {
    /**
     * Renders the field for an MpLocalDate
     */
    @KraftFormsDsl
    operator fun invoke(
        prop: KMutableProperty0<MpLocalDate>,
        builder: Options<MpLocalDate>.() -> Unit = {},
    ) = invoke(prop(), prop::set, builder)

    /**
     * Renders the field for an MpLocalDate
     */
    @KraftFormsDsl
    operator fun invoke(
        value: MpLocalDate,
        onChange: (MpLocalDate) -> Unit,
        builder: Options<MpLocalDate>.() -> Unit = {},
    ) = tag.UiInputField(value, onChange, ::dateToYmd, ::stringToDate) {
        asDateInput()
        builder()
    }

    /**
     * Renders the field for a nullable MpLocalDate
     */
    @KraftFormsDsl
    fun nullable(
        prop: KMutableProperty0<MpLocalDate?>,
        builder: Options<MpLocalDate?>.() -> Unit = {},
    ) = nullable(prop(), prop::set, builder)

    /**
     * Renders the field for a nullable MpLocalDate
     */
    @KraftFormsDsl
    fun nullable(
        value: MpLocalDate?,
        onChange: (MpLocalDate?) -> Unit,
        builder: Options<MpLocalDate?>.() -> Unit = {},
    ) = tag.UiInputField(value, onChange, ::dateToYmd, ::stringToDate) {
        asDateInput()
        builder()
    }

    /**
     * Renders the field for an MpLocalDateTime
     */
    @KraftFormsDsl
    operator fun invoke(
        prop: KMutableProperty0<MpLocalDateTime>,
        builder: SettingsBuilder<MpLocalDate> = {},
    ) = invoke(prop(), prop::set, builder)

    /**
     * Renders the field for an MpLocalDateTime
     */
    @KraftFormsDsl
    operator fun invoke(
        value: MpLocalDateTime,
        onChange: (MpLocalDateTime) -> Unit,
        builder: SettingsBuilder<MpLocalDate> = {},
    ) = invoke(
        value = value.toDate(),
        onChange = { onChange(it.atTime(value.toTime())) },
        builder = builder
    )

    /**
     * Renders the field for a nullable MpLocalDateTime
     */
    @KraftFormsDsl
    fun nullable(
        prop: KMutableProperty0<MpLocalDateTime?>,
        builder: SettingsBuilder<MpLocalDate?> = {},
    ) = nullable(prop(), prop::set, builder)

    /**
     * Renders the field for a nullable MpLocalDateTime
     */
    @KraftFormsDsl
    fun nullable(
        value: MpLocalDateTime?,
        onChange: (MpLocalDateTime?) -> Unit,
        builder: Options<MpLocalDate?>.() -> Unit = {},
    ) = nullable(
        value = value?.toDate(),
        onChange = {
            onChange(
                it?.atTime(value?.toTime() ?: MpLocalTime.Min)
            )
        },
        builder = builder
    )

    /**
     * Renders the field for an MpZonedDateTime
     */
    @KraftFormsDsl
    operator fun invoke(
        prop: KMutableProperty0<MpZonedDateTime>,
        builder: Options<MpLocalDate>.() -> Unit = {},
    ) = invoke(prop(), prop::set, builder)

    /**
     * Renders the field for an MpZonedDateTime
     */
    @KraftFormsDsl
    operator fun invoke(
        value: MpZonedDateTime,
        onChange: (MpZonedDateTime) -> Unit,
        builder: Options<MpLocalDate>.() -> Unit = {},
    ) = invoke(
        value = value,
        timezone = value.timezone,
        onChange = onChange,
        builder = builder
    )

    /**
     * Renders the field for an MpZonedDateTime
     */
    @KraftFormsDsl
    operator fun invoke(
        prop: KMutableProperty0<MpZonedDateTime>,
        timezone: MpTimezone,
        builder: Options<MpLocalDate>.() -> Unit = {},
    ) = invoke(prop(), timezone, prop::set, builder)

    /**
     * Renders the field for an MpZonedDateTime
     */
    @KraftFormsDsl
    operator fun invoke(
        value: MpZonedDateTime,
        timezone: MpTimezone,
        onChange: (MpZonedDateTime) -> Unit,
        builder: Options<MpLocalDate>.() -> Unit = {},
    ) = invoke(
        value = value.toLocalDate(),
        onChange = { onChange(it.atStartOfDay(timezone).atTime(value.toLocalTime())) },
        builder = builder
    )

    /**
     * Renders the field for a nullable MpZonedDateTime
     */
    @KraftFormsDsl
    fun nullable(
        prop: KMutableProperty0<MpZonedDateTime?>,
        timezone: MpTimezone,
        builder: Options<MpLocalDate?>.() -> Unit = {},
    ) = nullable(prop(), timezone, prop::set, builder)

    /**
     * Renders the field for a nullable MpZonedDateTime
     */
    @KraftFormsDsl
    fun nullable(
        value: MpZonedDateTime?,
        timezone: MpTimezone,
        onChange: (MpZonedDateTime?) -> Unit,
        builder: Options<MpLocalDate?>.() -> Unit = {},
    ) = nullable(
        value = value?.toLocalDate(),
        onChange = {
            onChange(
                it?.atStartOfDay(timezone)?.atTime(value?.toLocalTime() ?: MpLocalTime.Min)
            )
        },
        builder = builder
    )
}

class UiDateTimeFieldRenderer(private val tag: Tag) {
    /**
     * Renders the field for an MpLocalDateTime
     */
    @KraftFormsDsl
    operator fun invoke(
        prop: KMutableProperty0<MpLocalDateTime>,
        builder: Options<MpLocalDateTime>.() -> Unit = {},
    ) = invoke(prop(), prop::set, builder)

    /**
     * Renders the field for an MpLocalDateTime
     */
    @KraftFormsDsl
    operator fun invoke(
        value: MpLocalDateTime,
        onChange: (MpLocalDateTime) -> Unit,
        builder: Options<MpLocalDateTime>.() -> Unit = {},
    ) = tag.UiInputField(value, onChange, ::dateTimeToYmdHms, ::stringToLocalDateTime) {
        asDateTimeInput()
        builder()
    }

    /**
     * Renders the field for a nullable MpLocalDateTime
     */
    @KraftFormsDsl
    fun nullable(
        prop: KMutableProperty0<MpLocalDateTime?>,
        builder: Options<MpLocalDateTime?>.() -> Unit = {},
    ) = nullable(prop(), prop::set, builder)

    /**
     * Renders the field for a nullable MpLocalDateTime
     */
    @KraftFormsDsl
    fun nullable(
        value: MpLocalDateTime?,
        onChange: (MpLocalDateTime?) -> Unit,
        builder: Options<MpLocalDateTime?>.() -> Unit = {},
    ) = tag.UiInputField(value, onChange, ::dateTimeToYmdHms, ::stringToLocalDateTime) {
        asDateTimeInput()
        builder()
    }

    /**
     * Renders the field for an MpZonedDateTime
     */
    @KraftFormsDsl
    operator fun invoke(
        prop: KMutableProperty0<MpZonedDateTime>,
        builder: Options<MpZonedDateTime>.() -> Unit = {},
    ) = invoke(prop(), prop::set, builder)

    /**
     * Renders the field for an MpZonedDateTime
     */
    @KraftFormsDsl
    operator fun invoke(
        value: MpZonedDateTime,
        onChange: (MpZonedDateTime) -> Unit,
        builder: Options<MpZonedDateTime>.() -> Unit = {},
    ) = invoke(value, value.timezone, onChange, builder)

    /**
     * Renders the field for an MpZonedDateTime
     */
    @KraftFormsDsl
    operator fun invoke(
        prop: KMutableProperty0<MpZonedDateTime>,
        timezone: MpTimezone,
        builder: Options<MpZonedDateTime>.() -> Unit = {},
    ) = invoke(prop(), timezone, prop::set, builder)

    /**
     * Renders the field for an MpZonedDateTime
     */
    @KraftFormsDsl
    operator fun invoke(
        value: MpZonedDateTime,
        timezone: MpTimezone,
        onChange: (MpZonedDateTime) -> Unit,
        builder: Options<MpZonedDateTime>.() -> Unit = {},
    ) = tag.UiInputField(
        value,
        onChange,
        ::dateTimeToYmdHms,
        { stringToZonedDateTime(it).copy(timezone = timezone) },
    ) {
        asDateTimeInput()
        builder()
    }

    /**
     * Renders the field for a nullable MpZonedDateTime
     */
    @KraftFormsDsl
    fun nullable(
        prop: KMutableProperty0<MpZonedDateTime?>,
        timezone: MpTimezone,
        builder: Options<MpZonedDateTime?>.() -> Unit = {},
    ) = nullable(prop(), timezone, prop::set, builder)

    /**
     * Renders the field for a nullable MpZonedDateTime
     */
    @KraftFormsDsl
    fun nullable(
        value: MpZonedDateTime?,
        timezone: MpTimezone,
        onChange: (MpZonedDateTime?) -> Unit,
        builder: Options<MpZonedDateTime?>.() -> Unit = {},
    ) = tag.UiInputField(
        value,
        onChange,
        ::dateTimeToYmdHms,
        { stringToZonedDateTime(it).copy(timezone = timezone) },
    ) {
        asDateTimeInput()
        builder()
    }
}

class UiTimeFieldRenderer(private val tag: Tag) {
    /**
     * Renders the field for an MpLocalTime
     */
    @KraftFormsDsl
    operator fun invoke(
        prop: KMutableProperty0<MpLocalTime>,
        builder: Options<MpLocalTime>.() -> Unit = {},
    ) = invoke(prop(), prop::set, builder)

    /**
     * Renders the field for an MpLocalTime
     */
    @KraftFormsDsl
    operator fun invoke(
        value: MpLocalTime,
        onChange: (MpLocalTime) -> Unit,
        builder: Options<MpLocalTime>.() -> Unit = {},
    ) = tag.UiInputField(value, onChange, ::timeToHms, ::stringToLocalTime) {
        asTimeInput()
        builder()
    }

    /**
     * Renders the field for a nullable MpLocalTime
     */
    @KraftFormsDsl
    operator fun invoke(
        prop: KMutableProperty0<MpLocalTime?>,
        builder: Options<MpLocalTime?>.() -> Unit = {},
    ) = nullable(prop(), prop::set, builder)

    /**
     * Renders the field for a nullable MpLocalTime
     */
    @KraftFormsDsl
    fun nullable(
        value: MpLocalTime?,
        onChange: (MpLocalTime?) -> Unit,
        builder: Options<MpLocalTime?>.() -> Unit = {},
    ) = tag.UiInputField(value, onChange, ::timeToHms, ::stringToLocalTime) {
        asTimeInput()
        builder()
    }
}
