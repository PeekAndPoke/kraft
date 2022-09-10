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

    /**
     * Track if the input value was modified.
     *
     * 'True' means the value was modified and thus the field was touched.
     * 'False' means the value was not modified yet.
     */
    override var touched: Boolean by value(false)

    /**
     * A list of validation errors.
     */
    override var errors: List<String> by value(emptyList())

    /**
     * A unique dom key for the form field.
     */
    val domKey: String = getNextDomKey()

    /**
     * The input value set by the user.
     */
    private var inputValue: T? = null

    /**
     * The effective value
     *
     * - it is either a value set by the user (see [setValue])
     * - or the initial value coming through the props (see [Props.value])
     */
    val currentValue: T
        get() = when (val input = inputValue) {
            null -> props.value
            else -> input
        }

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    init {
        lifecycle {
            onMount {
                sendMessage(FormFieldMountedMessage(this@GenericFormField))
            }

            onUnmount {
                sendMessage(FormFieldUnmountedMessage(this@GenericFormField))
            }
        }
    }

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

    override fun VDom.render() {
    }

    fun focus(cssSelector: String) {
        (dom?.querySelector(cssSelector) as? HTMLElement)?.focus()
    }

    fun setValue(value: T) {
        touch()

        inputValue = value

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
