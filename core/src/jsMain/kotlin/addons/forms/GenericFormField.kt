package de.peekandpoke.kraft.addons.forms

import addons.forms.Settings
import addons.forms.SettingsBuilder
import de.peekandpoke.kraft.components.*
import de.peekandpoke.kraft.messages.sendMessage
import de.peekandpoke.kraft.utils.*
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.common.datetime.MpLocalDate
import de.peekandpoke.ultra.common.datetime.MpLocalDateTime
import kotlinx.html.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import kotlin.reflect.KMutableProperty0

@Suppress("FunctionName")
fun <T> Tag.GenericFormField(
    value: T,
    onChange: (T) -> Unit,
    toStr: (T) -> String,
    fromStr: (String) -> T,
    settings: Settings<T>,
    render: GenericFormField<T>.(VDom) -> Unit,
) = comp(
    GenericFormField.Props(
        value = value,
        onChange = onChange,
        toStr = toStr,
        fromStr = fromStr,
        render = render,
        settings = settings,
    )
) {
    GenericFormField(it)
}

class GenericFormField<T>(ctx: Ctx<Props<T>>) : FormField<T>, Component<GenericFormField.Props<T>>(ctx) {

    interface RendererAware {
        val tag: Tag
        val def: Definition
    }

    abstract class Renderer(override val tag: Tag, override val def: Definition) : RendererAware {

        interface ForStrings : RendererAware {
            operator fun invoke(
                prop: KMutableProperty0<String>,
                builder: SettingsBuilder<String>,
            ): ComponentRef<GenericFormField<String>> = invoke(prop(), prop::set, builder)

            operator fun invoke(
                value: String,
                onChange: (String) -> Unit,
                builder: SettingsBuilder<String>,
            ): ComponentRef<GenericFormField<String>> = def.run {
                tag.render(value, onChange, ::stringToString, ::stringToString, builder)
            }

            operator fun invoke(
                prop: KMutableProperty0<String?>,
                builder: SettingsBuilder<String?>,
            ): ComponentRef<GenericFormField<String?>> = invoke(prop(), prop::set, builder)

            operator fun invoke(
                value: String?,
                onChange: (String?) -> Unit,
                builder: SettingsBuilder<String?>,
            ): ComponentRef<GenericFormField<String?>> = def.run {
                tag.render(value, onChange, ::stringToString, ::stringToString, builder)
            }
        }

        interface ForNumbers : RendererAware {
            operator fun invoke(
                prop: KMutableProperty0<Int>,
                builder: SettingsBuilder<Int>,
            ): ComponentRef<GenericFormField<Int>> = invoke(prop(), prop::set, builder)

            operator fun invoke(
                value: Int,
                onChange: (Int) -> Unit,
                builder: SettingsBuilder<Int>,
            ): ComponentRef<GenericFormField<Int>> = def.run {
                tag.render(value, onChange, ::numberToString, ::stringToInt) {
                    input.type(InputType.number)
                    builder()
                }
            }

            operator fun invoke(
                prop: KMutableProperty0<Int?>,
                builder: SettingsBuilder<Int?>,
            ): ComponentRef<GenericFormField<Int?>> = invoke(prop(), prop::set, builder)

            operator fun invoke(
                value: Int?,
                onChange: (Int?) -> Unit,
                builder: SettingsBuilder<Int?>,
            ): ComponentRef<GenericFormField<Int?>> = def.run {
                tag.render(value, onChange, ::numberToString, ::stringToInt) {
                    input.type(InputType.number)
                    builder()
                }
            }

            operator fun invoke(
                prop: KMutableProperty0<Float>,
                builder: SettingsBuilder<Float>,
            ): ComponentRef<GenericFormField<Float>> = invoke(prop(), prop::set, builder)

            operator fun invoke(
                value: Float,
                onChange: (Float) -> Unit,
                builder: SettingsBuilder<Float>,
            ): ComponentRef<GenericFormField<Float>> = def.run {
                tag.render(value, onChange, ::numberToString, ::stringToFloat) {
                    input.type(InputType.number)
                    builder()
                }
            }

            operator fun invoke(
                prop: KMutableProperty0<Float?>,
                builder: SettingsBuilder<Float?>,
            ): ComponentRef<GenericFormField<Float?>> = invoke(prop(), prop::set, builder)

            operator fun invoke(
                value: Float?,
                onChange: (Float?) -> Unit,
                builder: SettingsBuilder<Float?>,
            ): ComponentRef<GenericFormField<Float?>> = def.run {
                tag.render(value, onChange, ::numberToString, ::stringToFloat) {
                    input.type(InputType.number)
                    builder()
                }
            }

            operator fun invoke(
                prop: KMutableProperty0<Double>,
                builder: SettingsBuilder<Double>,
            ): ComponentRef<GenericFormField<Double>> = invoke(prop(), prop::set, builder)

            operator fun invoke(
                value: Double,
                onChange: (Double) -> Unit,
                builder: SettingsBuilder<Double>,
            ): ComponentRef<GenericFormField<Double>> = def.run {
                tag.render(value, onChange, ::numberToString, ::stringToDouble) {
                    input.type(InputType.number)
                    builder()
                }
            }

            operator fun invoke(
                prop: KMutableProperty0<Double?>,
                builder: SettingsBuilder<Double?>,
            ): ComponentRef<GenericFormField<Double?>> = invoke(prop(), prop::set, builder)

            operator fun invoke(
                value: Double?,
                onChange: (Double?) -> Unit,
                builder: SettingsBuilder<Double?>,
            ): ComponentRef<GenericFormField<Double?>> = def.run {
                tag.render(value, onChange, ::numberToString, ::stringToDouble) {
                    input.type(InputType.number)
                    builder()
                }
            }

            operator fun invoke(
                prop: KMutableProperty0<Number>,
                builder: SettingsBuilder<Number>,
            ): ComponentRef<GenericFormField<Number>> = invoke(prop(), prop::set, builder)

            operator fun invoke(
                value: Number,
                onChange: (Number) -> Unit,
                builder: SettingsBuilder<Number>,
            ): ComponentRef<GenericFormField<Number>> = def.run {
                tag.render(value, onChange, ::numberToString, ::stringToNumber) {
                    input.type(InputType.number)
                    builder()
                }
            }

            operator fun invoke(
                prop: KMutableProperty0<Number?>,
                builder: SettingsBuilder<Number?>,
            ): ComponentRef<GenericFormField<Number?>> = invoke(prop(), prop::set, builder)

            operator fun invoke(
                value: Number?,
                onChange: (Number?) -> Unit,
                builder: SettingsBuilder<Number?>,
            ): ComponentRef<GenericFormField<Number?>> = def.run {
                tag.render(value, onChange, ::numberToString, ::stringToNumber) {
                    input.type(InputType.number)
                    builder()
                }
            }
        }

        interface ForDates : RendererAware {
            operator fun invoke(
                prop: KMutableProperty0<MpLocalDate>,
                builder: SettingsBuilder<MpLocalDate>,
            ): ComponentRef<GenericFormField<MpLocalDate>> =
                invoke(prop(), prop::set, builder)

            operator fun invoke(
                value: MpLocalDate,
                onChange: (MpLocalDate) -> Unit,
                builder: SettingsBuilder<MpLocalDate>,
            ): ComponentRef<GenericFormField<MpLocalDate>> = def.run {
                tag.render(value, onChange, ::dateToYmd, ::stringToDate) {
                    input.asDateInput()
                    builder()
                }
            }

            operator fun invoke(
                value: MpLocalDateTime,
                onChange: (MpLocalDateTime) -> Unit,
                builder: SettingsBuilder<MpLocalDate>,
            ): ComponentRef<GenericFormField<MpLocalDate>> =
                invoke(
                    value = value.toDate(),
                    onChange = { onChange(it.atTime(value.toTime())) },
                    builder = builder
                )
        }

        interface ForDateTimes : RendererAware {
            operator fun invoke(
                prop: KMutableProperty0<MpLocalDateTime>,
                builder: SettingsBuilder<MpLocalDateTime>,
            ): ComponentRef<GenericFormField<MpLocalDateTime>> =
                invoke(prop(), prop::set, builder)

            operator fun invoke(
                value: MpLocalDateTime,
                onChange: (MpLocalDateTime) -> Unit,
                builder: SettingsBuilder<MpLocalDateTime>,
            ): ComponentRef<GenericFormField<MpLocalDateTime>> = def.run {
                tag.render(value, onChange, ::dateTimeToYmdHms, ::stringToDateTime) {
                    input.asDateTimeInput()
                    builder()
                }
            }
        }
    }

    interface Definition {
        fun <T> Tag.render(
            value: T,
            onChange: (T) -> Unit,
            toStr: (T) -> String,
            fromStr: (String) -> T,
            settings: Settings<T>.() -> Unit,
        ): ComponentRef<GenericFormField<T>> {
            return comp(
                Props(
                    value = value,
                    onChange = onChange,
                    toStr = toStr,
                    fromStr = fromStr,
                    settings = Settings<T>().apply {
                        settings()
                    },
                    render = { content(it) },
                )
            ) {
                GenericFormField(it)
            }
        }

        fun <T> GenericFormField<T>.content(vdom: VDom)
    }

    //  PROPS  //////////////////////////////////////////////////////////////////////////////////////////////////

    data class Props<T>(
        val value: T,
        val onChange: (T) -> Unit,
        val toStr: (T) -> String,
        val fromStr: (String) -> T,
        val settings: Settings<T>,
        val render: GenericFormField<T>.(VDom) -> Unit,
    )

    val settings: Settings<T> get() = props.settings

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    class InputValue<T>(val value: T)

    override var touched by value(false)

    override var errors: List<String> by value(emptyList())

    private var inputValue: InputValue<T>? = null

    val currentValue
        get() = when (val input = inputValue) {
            null -> props.value
            else -> input.value
        }

    fun valueAsString(): String {
        return props.toStr(currentValue)
    }

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun touch() {
        touched = true
    }

    override fun untouch() {
        touched = false
    }

    override fun validate(): Boolean {

        if (touched) {
            errors = props.settings.rules
                .filter { !it.check(currentValue) }
                .map { it.getMessage(currentValue) }
        }

        return errors.isEmpty()
    }

    override fun onMount() {
        sendMessage(FormFieldMountedMessage(this))
    }

    override fun onUnmount() {
        sendMessage(FormFieldUnmountedMessage(this))
    }

    override fun VDom.render() {
        props.render(this@GenericFormField, this)
    }

    fun focus(cssSelector: String) {
        (dom?.querySelector(cssSelector) as? HTMLElement)?.focus()
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

    fun setValue(value: T) {
        touch()

        inputValue = InputValue(value)

        if (validate()) {
            props.onChange(currentValue)
        }
    }

    // Label Helpers

    fun FlowContent.renderLabel(focusCssSelector: String? = null) {
        settings.label?.let { l ->
            label {
                l()
                focusCssSelector?.let { selector ->
                    focusOnClick(selector)
                }
            }
        }
    }

    fun LABEL.focusOnClick(cssSelector: String) {
        onClick {
            focus(cssSelector)
        }
    }

    // INPUT helpers

    fun INPUT.applyAll() {
        setValue()
        track()

        applyPlaceholder()

        applyType()
        applyStep()
        applyFormatValue()
    }

    fun INPUT.track() {
        onInput {
            setInput((it.target as HTMLInputElement).value)
        }
    }

    fun INPUT.setValue() {
        value = valueAsString()
    }

    fun INPUT.applyPlaceholder() {
        settings.placeholder?.takeIf { it.isNotBlank() }?.let {
            placeholder = it
        }
    }

    fun INPUT.applyType() {
        settings.input.type?.let { type = it }
    }

    fun INPUT.applyStep() {
        settings.input.step?.let {
            step = it.toString()
        }
    }

    fun INPUT.applyFormatValue() {
        settings.input.formatValue?.let {
            attributes["format-value"] = it
        }
    }

    // TEXTAREA helpers

    fun TEXTAREA.applyAll() {
        track()
        applyPlaceholder()
    }

    fun TEXTAREA.track() {
        onInput {
            setInput((it.target as HTMLTextAreaElement).value)
        }
    }

    fun TEXTAREA.applyPlaceholder() {
        settings.placeholder?.takeIf { it.isNotBlank() }?.let {
            placeholder = it
        }
    }
}
