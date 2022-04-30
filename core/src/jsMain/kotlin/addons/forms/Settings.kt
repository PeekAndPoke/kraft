package addons.forms

import de.peekandpoke.kraft.addons.forms.KraftFormsSettingDsl
import de.peekandpoke.kraft.addons.forms.validation.Rule
import de.peekandpoke.kraft.components.RenderFunc
import de.peekandpoke.ultra.common.MutableTypedAttributes
import de.peekandpoke.ultra.common.TypedKey
import kotlinx.html.InputType
import kotlinx.html.LABEL

open class Settings<T> {
    companion object {
        private val RulesKey = TypedKey<MutableList<Rule<*>>>("rules")
        private val LabelKey = TypedKey<RenderFunc<LABEL>>("label")
        private val PlaceholderKey = TypedKey<String>("placeholder")
        private val InputSettingsKey = TypedKey<InputSettings>("input")
    }

    val attributes: MutableTypedAttributes = MutableTypedAttributes.empty()

    @KraftFormsSettingDsl
    val rules: MutableList<Rule<T>>
        get() {
            @Suppress("UNCHECKED_CAST")
            return attributes.getOrPut(RulesKey) { mutableListOf() } as MutableList<Rule<T>>
        }

    /** Adds a validation rule */
    @KraftFormsSettingDsl
    fun accepts(vararg rules: Rule<T>) = apply {
        this.rules.addAll(rules)
    }

    @KraftFormsSettingDsl
    val label get() = attributes[LabelKey]

    @KraftFormsSettingDsl
    fun label(render: RenderFunc<LABEL>) = apply {
        attributes[LabelKey] = render
    }

    @KraftFormsSettingDsl
    fun label(label: String) = apply {
        attributes[LabelKey] = { +label }
    }

    @KraftFormsSettingDsl
    val placeholder get() = attributes[PlaceholderKey]

    @KraftFormsSettingDsl
    fun placeholder(placeholder: String) = apply {
        attributes[PlaceholderKey] = placeholder
    }

    @KraftFormsSettingDsl
    val input get(): InputSettings = attributes.getOrPut(InputSettingsKey) { InputSettings() }
}

class InputSettings {
    companion object {
        private val typeKey = TypedKey<InputType>("type")
        private val stepKey = TypedKey<Number>("step")
    }

    val attributes = MutableTypedAttributes.empty()

    @KraftFormsSettingDsl
    val type get() = attributes[typeKey]

    @KraftFormsSettingDsl
    fun type(type: InputType) = apply {
        attributes[typeKey] = type
    }

    @KraftFormsSettingDsl
    val step get() = attributes[stepKey]

    @KraftFormsSettingDsl
    fun step(step: Number) = apply {
        attributes[stepKey] = step
    }
}
