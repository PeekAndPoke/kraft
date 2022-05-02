package addons.forms

import de.peekandpoke.kraft.addons.forms.KraftFormsSettingDsl
import de.peekandpoke.kraft.addons.forms.validation.Rule
import de.peekandpoke.kraft.components.RenderFunc
import de.peekandpoke.ultra.common.MutableTypedAttributes
import de.peekandpoke.ultra.common.TypedKey
import kotlinx.html.InputType
import kotlinx.html.LABEL

typealias SettingsBuilder<T> = Settings<T>.() -> Unit

abstract class SettingsBase<T> {
    private val attributes: MutableTypedAttributes = MutableTypedAttributes.empty()

    fun <X> set(key: TypedKey<X>, value: X) {
        attributes[key] = value
    }

    fun <X> get(key: TypedKey<X>): X? {
        return attributes[key]
    }

    fun <X> getOrPut(key: TypedKey<X>, produce: () -> X): X {
        return attributes.getOrPut(key, produce)
    }
}

open class Settings<T> : SettingsBase<T>() {
    companion object {
        private val InputSettingsKey = TypedKey<InputSettings<*>>("input")
    }

    @KraftFormsSettingDsl
    val rules: MutableList<Rule<T>> = mutableListOf()

    @KraftFormsSettingDsl
    var label: RenderFunc<LABEL>? = null
        private set

    @KraftFormsSettingDsl
    var placeholder: String? = null
        private set

    /** Adds a validation rule */
    @KraftFormsSettingDsl
    fun accepts(vararg rules: Rule<T>) = apply {
        this.rules.addAll(rules)
    }

    @KraftFormsSettingDsl
    fun label(render: RenderFunc<LABEL>) = apply {
        label = render
    }

    @KraftFormsSettingDsl
    fun label(label: String) = label { +label }

    @KraftFormsSettingDsl
    fun placeholder(placeholder: String) = apply {
        this.placeholder = placeholder
    }

    @KraftFormsSettingDsl
    val input
        get(): InputSettings<T> {
            @Suppress("UNCHECKED_CAST")
            return getOrPut(InputSettingsKey) { InputSettings<T>() } as InputSettings<T>
        }
}

class InputSettings<T> : SettingsBase<T>() {
    companion object {
        private val typeKey = TypedKey<InputType>("type")
        private val stepKey = TypedKey<Number>("step")
        private val formatValueKey = TypedKey<String>("format-value")
    }

    @KraftFormsSettingDsl
    var type: InputType? = null
        private set

    @KraftFormsSettingDsl
    var step: Number? = null
        private set

    @KraftFormsSettingDsl
    var formatValue: String? = null
        private set

    @KraftFormsSettingDsl
    fun asDateInput() {
        type(InputType.date)
        formatValue("yyyy-MM-dd")
    }

    @KraftFormsSettingDsl
    fun asDateTimeInput() {
        type(InputType.dateTimeLocal)
        formatValue("yyyy-MM-ddTHH:mm:ss")
    }

    @KraftFormsSettingDsl
    fun asTimeInput() {
        type(InputType.time)
        formatValue("HH:mm:ss")
    }

    @KraftFormsSettingDsl
    fun type(type: InputType) = apply {
        this.type = type
    }

    @KraftFormsSettingDsl
    fun step(step: Number) = apply {
        this.step = step
    }

    @KraftFormsSettingDsl
    fun formatValue(formatValue: String) = apply {
        this.formatValue = formatValue
    }
}
