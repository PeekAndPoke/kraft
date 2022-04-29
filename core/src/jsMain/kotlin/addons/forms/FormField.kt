package de.peekandpoke.kraft.addons.forms

import de.peekandpoke.kraft.addons.forms.validation.Rule
import de.peekandpoke.kraft.components.RenderFunc
import de.peekandpoke.ultra.common.MutableTypedAttributes
import de.peekandpoke.ultra.common.TypedKey
import kotlinx.html.InputType
import kotlinx.html.LABEL

interface FormField<T> {

    interface SettingsAware<T> {
        val attributes: MutableTypedAttributes
    }

    class Settings<T> : SettingsAware<T> {
        override val attributes: MutableTypedAttributes = MutableTypedAttributes.empty()

        companion object {

            private val RulesKey = TypedKey<MutableList<Rule<*>>>("rules")

            val <T> Settings<T>.rules: MutableList<Rule<T>>
                get() {
                    @Suppress("UNCHECKED_CAST")
                    return attributes.getOrPut(RulesKey) { mutableListOf() } as MutableList<Rule<T>>
                }

            /** Adds a validation rule */
            fun <T> Settings<T>.accepts(vararg rules: Rule<T>) = apply {
                this.rules.addAll(rules)
            }

            private val LabelKey = TypedKey<RenderFunc<LABEL>>("label")

            val Settings<*>.label get() = attributes[LabelKey]

            fun Settings<*>.label(render: RenderFunc<LABEL>) = apply {
                attributes[LabelKey] = render
            }

            fun Settings<*>.label(label: String) = apply {
                attributes[LabelKey] = { +label }
            }

            private val InputType = TypedKey<InputType>("input-type")

            val Settings<*>.inputType get() = attributes[InputType]

            fun Settings<*>.inputType(type: InputType) = apply {
                attributes[InputType] = type
            }

            private val PlaceholderKey = TypedKey<String>("placeholder")

            val Settings<*>.placeholder get() = attributes[PlaceholderKey]

            fun Settings<*>.placeholder(placeholder: String) = apply {
                attributes[PlaceholderKey] = placeholder
            }

            private val InputStepKey = TypedKey<Double>("input-step")

            val Settings<*>.inputStep get() = attributes[InputStepKey]

            fun Settings<*>.inputStep(step: Double) = apply {
                attributes[InputStepKey] = step
            }
        }
    }

    /**
     * The errors the field currently has
     */
    val errors: List<String>

    /**
     * Returns true when the field has errors
     */
    val hasErrors get() = errors.isNotEmpty()

    /**
     * Marker if the component was touched.
     */
    val touched: Boolean

    /**
     * Marks the form field as touched.
     */
    fun touch()

    /**
     * Marks the form field as not touched.
     */
    fun untouch()

    /**
     * Validates all rules and returns true if all rules are fulfilled.
     */
    fun validate(): Boolean
}
