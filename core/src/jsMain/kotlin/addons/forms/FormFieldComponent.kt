package de.peekandpoke.kraft.addons.forms

import de.peekandpoke.kraft.addons.forms.validation.Rule
import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.components.Ctx
import de.peekandpoke.kraft.messages.sendMessage

abstract class FormFieldComponent<T, P : FormFieldComponent.Props<T>>(ctx: Ctx<P>) : Component<P>(ctx) {

    ////  PROPS  //////////////////////////////////////////////////////////////////////////////////////////////////

    interface Props<P> {
        val initialValue: P
        val fromStr: (String) -> P
        val onChange: (P) -> Unit
        val rules: List<Rule<P>>
    }

    class InputValue<T>(val value: T)

    ////  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    private var inputValue: InputValue<T>? = null

    val currentValue
        get() = when (val input = inputValue) {
            null -> props.initialValue
            else -> input.value
        }

    var touched by value(false)
    var errors by value<List<String>>(emptyList())

    val hasErrors get() = errors.isNotEmpty()

    ////  LIVE-CYCLE  /////////////////////////////////////////////////////////////////////////////////////////////

    override fun onNextProps(newProps: P, previousProps: P) {
        // A changing input value overrides the current input
        if (newProps.initialValue != previousProps.initialValue) {
            // Override the current user input
            inputValue = null
            // Notify the form controller about the change
            sendMessage(FormFieldInputChanged(this))
        }
    }

    override fun onMount() {
        sendMessage(FormFieldMountedMessage(this))
    }

    override fun onUnmount() {
        sendMessage(FormFieldUnmountedMessage(this))
    }

    ////  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

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

    fun touch() {
        touched = true
    }

    fun validate(): Boolean {
        if (touched) {
            errors = props.rules
                .filter { !it.check(currentValue) }
                .map { it.getMessage(currentValue) }
        }

        return errors.isEmpty()
    }

    ////  RENDERING  ////////////////////////////////////////////////////////////////////////////////////////////////
}
