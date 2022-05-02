package de.peekandpoke.kraft.addons.semanticui.forms.old.textarea

import de.peekandpoke.kraft.addons.forms.FormFieldComponent
import de.peekandpoke.kraft.addons.forms.validation.Rule
import de.peekandpoke.kraft.addons.semanticui.forms.renderErrors
import de.peekandpoke.kraft.addons.styling.css
import de.peekandpoke.kraft.components.Ctx
import de.peekandpoke.kraft.components.onInput
import de.peekandpoke.kraft.utils.launch
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.css.Overflow
import kotlinx.css.maxHeight
import kotlinx.css.overflowY
import kotlinx.css.vh
import kotlinx.html.*
import org.w3c.dom.HTMLTextAreaElement

open class TextAreaFieldComponent<T>(ctx: Ctx<Props<T>>) : FormFieldComponent<T, TextAreaFieldComponent.Props<T>>(ctx) {

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

        /** The input value as string */
        val stringValue get() = toStr(value)

        /** The label renderer of the field */
        val label get() = _label

        /** The placeholder of the field */
        val placeholder get() = _placeholder

        /** Flag whether the textarea should automatically resize vertically */
        var verticalAutoResize = true

        /** The label renderer of the field */
        private var _label: LABEL.() -> Unit = {}

        /** The placeholder of the field */
        private var _placeholder: String = ""

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

        /** Adds a validation rule */
        fun accepts(rule: Rule<T>, vararg rules: Rule<T>) = apply {
            this.rules.add(rule)
            this.rules.addAll(rules)
        }
    }

    override fun VDom.render() {
        ui.given(hasErrors) { error }.field {

            label {
                apply(props.config.label)

                textArea {
                    onInput {
                        setInput((it.target as HTMLTextAreaElement).value)
                    }

                    css {
                        maxHeight = 50.vh
                        overflowY = Overflow.auto
                    }

                    +props.config.stringValue

                    if (props.config.placeholder.isNotBlank()) {
                        placeholder = props.config.placeholder
                    }
                }
            }

            renderErrors(this@TextAreaFieldComponent, this)
        }

        // Apply automatic vertical resize
        if (props.config.verticalAutoResize) {
            window.requestAnimationFrame {
                launch {
                    delay(10) // NOTE: this is a bit hacky...

                    val textarea = dom?.getElementsByTagName("textarea")?.item(0) as? HTMLTextAreaElement

                    textarea?.let { t ->
                        t.style.height = "0"
                        t.style.height = "${t.scrollHeight + 6}px"
                    }
                }
            }
        }
    }
}
