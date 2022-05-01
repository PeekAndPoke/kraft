package de.peekandpoke.kraft.addons.forms

import addons.forms.Settings
import addons.forms.SettingsBuilder
import de.peekandpoke.kraft.components.*
import de.peekandpoke.kraft.messages.sendMessage
import de.peekandpoke.kraft.utils.*
import de.peekandpoke.kraft.vdom.VDom
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
            operator fun invoke(prop: KMutableProperty0<String>, builder: SettingsBuilder<String>) =
                def.render(tag, prop(), prop::set, builder)

            operator fun invoke(value: String, onChange: (String) -> Unit, builder: SettingsBuilder<String>) =
                def.render(tag, value, onChange, builder)

            operator fun invoke(prop: KMutableProperty0<String?>, builder: SettingsBuilder<String?>) =
                def.render(tag, prop(), prop::set, builder)

            operator fun invoke(value: String?, onChange: (String?) -> Unit, builder: SettingsBuilder<String?>) =
                def.render(tag, value, onChange, builder)
        }

        interface ForNumbers : RendererAware {
            operator fun invoke(prop: KMutableProperty0<Int>, builder: SettingsBuilder<Int>) =
                def.render(tag, prop(), prop::set, builder)

            operator fun invoke(value: Int, onChange: (Int) -> Unit, builder: SettingsBuilder<Int>) =
                def.render(tag, value, onChange, builder)

            operator fun invoke(prop: KMutableProperty0<Int?>, builder: SettingsBuilder<Int?>) =
                def.render(tag, prop(), prop::set, builder)

            operator fun invoke(value: Int?, onChange: (Int?) -> Unit, builder: SettingsBuilder<Int?>) =
                def.render(tag, value, onChange, builder)

            operator fun invoke(prop: KMutableProperty0<Float>, builder: SettingsBuilder<Float>) =
                def.render(tag, prop(), prop::set, builder)

            operator fun invoke(value: Float, onChange: (Float) -> Unit, builder: SettingsBuilder<Float>) =
                def.render(tag, value, onChange, builder)

            operator fun invoke(prop: KMutableProperty0<Float?>, builder: SettingsBuilder<Float?>) =
                def.render(tag, prop(), prop::set, builder)

            operator fun invoke(value: Float?, onChange: (Float?) -> Unit, builder: SettingsBuilder<Float?>) =
                def.render(tag, value, onChange, builder)

            operator fun invoke(prop: KMutableProperty0<Double>, builder: SettingsBuilder<Double>) =
                def.render(tag, prop(), prop::set, builder)

            operator fun invoke(value: Double, onChange: (Double) -> Unit, builder: SettingsBuilder<Double>) =
                def.render(tag, value, onChange, builder)

            operator fun invoke(prop: KMutableProperty0<Double?>, builder: SettingsBuilder<Double?>) =
                def.render(tag, prop(), prop::set, builder)

            operator fun invoke(value: Double?, onChange: (Double?) -> Unit, builder: SettingsBuilder<Double?>) =
                def.render(tag, value, onChange, builder)

            operator fun invoke(prop: KMutableProperty0<Number>, builder: SettingsBuilder<Number>) =
                def.render(tag, prop(), prop::set, builder)

            operator fun invoke(value: Number, onChange: (Number) -> Unit, builder: SettingsBuilder<Number>) =
                def.render(tag, value, onChange, builder)

            operator fun invoke(prop: KMutableProperty0<Number?>, builder: SettingsBuilder<Number?>) =
                def.render(tag, prop(), prop::set, builder)

            operator fun invoke(value: Number?, onChange: (Number?) -> Unit, builder: SettingsBuilder<Number?>) =
                def.render(tag, value, onChange, builder)
        }
    }

    interface Definition {
        fun <T> Tag.render(
            value: T,
            onChange: (T) -> Unit,
            toStr: (T) -> String,
            fromStr: (String) -> T,
            settings: Settings<T>.() -> Unit,
        ) {
            comp(
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

        fun render(tag: Tag, value: String, onChange: (String) -> Unit, builder: SettingsBuilder<String>) {
            with(tag) {
                render(value, onChange, ::stringToString, ::stringToString, builder)
            }
        }

        fun render(tag: Tag, value: String?, onChange: (String?) -> Unit, builder: SettingsBuilder<String?>) {
            with(tag) {
                render(value, onChange, ::stringToString, ::stringToString, builder)
            }
        }

        fun render(tag: Tag, value: Int, onChange: (Int) -> Unit, builder: SettingsBuilder<Int>) {
            with(tag) {
                render(value, onChange, ::numberToString, ::stringToInt) {
                    input.type(InputType.number)
                    builder()
                }
            }
        }

        fun render(tag: Tag, value: Int?, onChange: (Int?) -> Unit, builder: SettingsBuilder<Int?>) {
            with(tag) {
                render(value, onChange, ::numberToString, ::stringToInt) {
                    input.type(InputType.number)
                    builder()
                }
            }
        }

        fun render(tag: Tag, value: Float, onChange: (Float) -> Unit, builder: SettingsBuilder<Float>) {
            with(tag) {
                render(value, onChange, ::numberToString, ::stringToFloat) {
                    input.type(InputType.number)
                    builder()
                }
            }
        }

        fun render(tag: Tag, value: Float?, onChange: (Float?) -> Unit, builder: SettingsBuilder<Float?>) {
            with(tag) {
                render(value, onChange, ::numberToString, ::stringToFloat) {
                    input.type(InputType.number)
                    builder()
                }
            }
        }

        fun render(tag: Tag, value: Double, onChange: (Double) -> Unit, builder: SettingsBuilder<Double>) {
            with(tag) {
                render(value, onChange, ::numberToString, ::stringToDouble) {
                    input.type(InputType.number)
                    builder()
                }
            }
        }

        fun render(tag: Tag, value: Double?, onChange: (Double?) -> Unit, builder: SettingsBuilder<Double?>) {
            with(tag) {
                render(value, onChange, ::numberToString, ::stringToDouble) {
                    input.type(InputType.number)
                    builder()
                }
            }
        }

        fun render(tag: Tag, value: Number, onChange: (Number) -> Unit, builder: SettingsBuilder<Number>) {
            with(tag) {
                render(value, onChange, ::numberToString, ::stringToNumber) {
                    input.type(InputType.number)
                    builder()
                }
            }
        }

        fun render(tag: Tag, value: Number?, onChange: (Number?) -> Unit, builder: SettingsBuilder<Number?>) {
            with(tag) {
                render(value, onChange, ::numberToString, ::stringToNumber) {
                    input.type(InputType.number)
                    builder()
                }
            }
        }
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
