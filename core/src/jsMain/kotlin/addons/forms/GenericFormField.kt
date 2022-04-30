package de.peekandpoke.kraft.addons.forms

import addons.forms.Settings
import de.peekandpoke.kraft.components.*
import de.peekandpoke.kraft.messages.sendMessage
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement

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

    private var inputValue: FormFieldComponent.InputValue<T>? = null

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

        inputValue = FormFieldComponent.InputValue(value)

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

        applyType()
        applyPlaceholder()
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

    fun INPUT.applyType() {
        settings.input.type?.let { type = it }
    }

    fun INPUT.applyPlaceholder() {
        settings.placeholder?.takeIf { it.isNotBlank() }?.let {
            placeholder = it
        }
    }

    fun INPUT.applyStep() {
        settings.input.step?.let {
            step = it.toString()
        }
    }
}
