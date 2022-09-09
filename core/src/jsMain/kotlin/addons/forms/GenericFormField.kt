package de.peekandpoke.kraft.addons.forms

import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.components.Ctx
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.onClick
import de.peekandpoke.kraft.messages.sendMessage
import de.peekandpoke.kraft.semanticui.noui
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.MouseEvent

@Suppress("FunctionName")
fun <T> Tag.GenericFormField(
    value: T,
    onChange: (T) -> Unit,
    options: FieldOptions<T>,
) = comp(
    GenericFormField.DefaultProps(
        value = value,
        onChange = onChange,
        options = options,
    )
) {
    GenericFormField(it)
}

open class GenericFormField<T, O : FieldOptions<T>, P : GenericFormField.Props<T, O>>(
    ctx: Ctx<P>
) : Component<P>(ctx), FormField<T> {

    companion object {
        private var nextDomKey: Int = 0

        internal fun getNextDomKey(): String = "form-field-$nextDomKey".also { nextDomKey += 1 }
    }

    //  PROPS  //////////////////////////////////////////////////////////////////////////////////////////////////

    interface Props<T, O : FieldOptions<T>> {
        val value: T
        val onChange: (T) -> Unit
        val options: O
    }

    data class DefaultProps<T, O : FieldOptions<T>>(
        override val value: T,
        override val onChange: (T) -> Unit,
        override val options: O,
    ) : Props<T, O>

    val options: O get() = props.options

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    class InputValue<T>(val value: T)

    override var touched: Boolean by value(false)

    override var errors: List<String> by value(emptyList())

    private var inputValue: InputValue<T>? = null

    val domKey: String = getNextDomKey()

    val currentValue: T
        get() = when (val input = inputValue) {
            null -> props.value
            else -> input.value
        }

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun reset() {
        touched = false
        inputValue = null
        errors = emptyList()
    }

    override fun touch() {
        touched = true
    }

    override fun untouch() {
        touched = false
    }

    override fun validate(): Boolean {

        if (touched) {
            errors = props.options.rules
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
    }

    fun focus(cssSelector: String) {
        (dom?.querySelector(cssSelector) as? HTMLElement)?.focus()
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

        renderLabel(
            onClick = focusCssSelector?.let { sel ->
                { focus(sel) }
            }
        )
    }

    fun FlowContent.renderLabel(onClick: ((evt: MouseEvent) -> Unit)? = null) {
        options.label()?.let { l ->
            noui Label {
                l()

                onClick?.let { on ->
                    onClick { evt ->
                        on(evt)
                    }
                }
            }
        }
    }
}
