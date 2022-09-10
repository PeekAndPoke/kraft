package de.peekandpoke.kraft.addons.semanticui.forms.old.input

import de.peekandpoke.kraft.addons.forms.FormFieldComponent
import de.peekandpoke.kraft.addons.forms.validation.Rule
import de.peekandpoke.kraft.addons.semanticui.forms.renderErrors
import de.peekandpoke.kraft.components.Ctx
import de.peekandpoke.kraft.components.key
import de.peekandpoke.kraft.components.onClick
import de.peekandpoke.kraft.components.onInput
import de.peekandpoke.kraft.semanticui.SemanticTag
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.*
import org.w3c.dom.HTMLInputElement

@Suppress("DEPRECATION")
@Deprecated(message = "Switch to UiInputField")
open class InputFieldComponent<T>(ctx: Ctx<Props<T>>) : FormFieldComponent<T, InputFieldComponent.Props<T>>(ctx) {

    data class Props<P>(
        val config: Config<P>,
    ) : FormFieldComponent.Props<P> {
        override val initialValue: P = config.value
        override val fromStr: (String) -> P get() = config.fromStr
        override val onChange: (P) -> Unit get() = config.onChange
        override val rules: List<Rule<P>> get() = config.rules
    }

    class Config<T>(
        var value: T,
        var type: InputType,
        val onChange: (T) -> Unit,
        val toStr: (T) -> String,
        val fromStr: (String) -> T
    ) {
        val asProps get() = Props(config = this)

        /** The validation rules */
        val rules: MutableList<Rule<T>> = mutableListOf()

        /** The label renderer of the field */
        val label: (LABEL.() -> Unit)? get() = _label

        /** The placeholder of the field */
        val placeholder: String get() = _placeholder

        /** The appearance of the input field */
        val appearance: SemanticTag.() -> SemanticTag get() = _appearance

        /** The step size in case of a numeric field */
        val step: Number? get() = _step

        /** Callback for rendering an icon on the right side of the input field */
        val rightIcon: (DIV.(field: InputFieldComponent<T>) -> Unit)? get() = _rightIcon

        /** The label renderer of the field */
        private var _label: (LABEL.() -> Unit)? = null

        /** The placeholder of the field */
        private var _placeholder: String = ""

        /** The appearance of the input field */
        private var _appearance: SemanticTag.() -> SemanticTag = { this }

        /** The step size in case of a numeric field */
        private var _step: Double? = null

        /** Callback for rendering an icon on the right side of the input field */
        private var _rightIcon: (DIV.(field: InputFieldComponent<T>) -> Unit)? = null

        /** Sets the label */
        fun label(label: String) {
            _label = { +label }
        }

        /** Sets the label as a render function */
        fun label(rendered: LABEL.() -> Unit) {
            _label = rendered
        }

        /** Sets the Placeholder */
        fun placeholder(placeholder: String) {
            _placeholder = placeholder
        }

        /** Sets the appearance of the input field */
        fun appear(appearance: SemanticTag.() -> SemanticTag) = apply {
            _appearance = appearance
        }

        /** Sets the step size for numeric fields */
        fun step(step: Double) {
            _step = step
        }

        /** Sets the step size for numeric fields */
        fun step(step: Int) = step(step.toDouble())

        /** Adds a validation rule */
        fun accepts(rule: Rule<T>, vararg rules: Rule<T>) = apply {
            this.rules.add(rule)
            this.rules.addAll(rules)
        }

        /** Sets the callback for rendering an icon on the right side of the input field */
        fun rightIcon(block: DIV.(field: InputFieldComponent<T>) -> Unit) {
            _rightIcon = block
        }
    }

    override fun VDom.render() {

        ui.with(props.config.appearance).given(hasErrors) { error }.field {
            key = domKey

            val l = props.config.label

            if (l != null) {
                label {
                    apply(l)

                    onClick {
                        (dom?.querySelector("input") as? HTMLInputElement)?.focus()
                    }
                }
            }

            when {
                props.config.rightIcon != null -> renderInputWithRightIcon()

                else -> renderInput()
            }

            renderErrors(this@InputFieldComponent, this)
        }
    }

    protected open fun FlowContent.renderInput() {

        input {
            onInput { setInput((it.target as HTMLInputElement).value) }

            type = props.config.type

            this.value = props.config.toStr(currentValue)

            props.config.step?.let { step = it.toString() }

            props.config.placeholder.takeIf { it.isNotBlank() }?.let {
                placeholder = it
            }
        }
    }

    protected open fun FlowContent.renderInputWithRightIcon() {

        ui.input.right.icon {
            props.config.rightIcon?.invoke(this, this@InputFieldComponent)

            renderInput()
        }
    }
}
