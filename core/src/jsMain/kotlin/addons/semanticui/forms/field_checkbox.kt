package de.peekandpoke.kraft.addons.semanticui.forms

import addons.forms.SettingsBuilder
import de.peekandpoke.kraft.addons.forms.GenericFormField
import de.peekandpoke.kraft.addons.forms.KraftFormsDsl
import de.peekandpoke.kraft.components.ComponentRef
import de.peekandpoke.kraft.components.onChange
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.InputType
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.input
import org.w3c.dom.HTMLInputElement

@KraftFormsDsl
val Tag.UiCheckboxField get() = UiCheckboxFieldDef.InputRenderer(this)

/**
 * Semantic ui input field definition
 */
object UiCheckboxFieldDef : GenericFormField.Definition {

    class InputRenderer(tag: Tag) : GenericFormField.Renderer(tag, UiCheckboxFieldDef) {

        /**
         * Renders the field for an Int
         */
        operator fun invoke(
            value: Boolean,
            onChange: (Boolean) -> Unit,
            builder: SettingsBuilder<Boolean>,
        ): ComponentRef<GenericFormField<Boolean>> = def.run {
            tag.render(value, onChange, { "" }, { false }) {
                semantic.checkbox.options(off = false, on = true)
                builder()
            }
        }

        /**
         * Renders the field for an Int
         */
        operator fun <T> invoke(
            value: T,
            options: Pair<T, T>,
            onChange: (T) -> Unit,
            builder: SettingsBuilder<T>,
        ): ComponentRef<GenericFormField<T>> = def.run {
            tag.render(value, onChange, { "" }, { options.first }) {
                semantic.checkbox.options(off = options.first, on = options.second)
                builder()
            }
        }
    }

    override fun <T> GenericFormField<T>.content(vdom: VDom) {

        val offValue = settings.semantic.checkbox.options?.first
        val onValue = settings.semantic.checkbox.options?.second

        with(vdom) {

            ui.with(settings.semantic.appear).given(hasErrors) { error }.field {
                div {
                    ui.with(settings.semantic.checkbox.style).checkbox {
                        input {
                            onChange {
                                when ((it.target as HTMLInputElement).checked) {
                                    true -> onValue?.let { v -> setValue(v) }
                                    false -> offValue?.let { v -> setValue(v) }
                                }
                            }
                            type = InputType.checkBox
                            checked = currentValue == onValue
                        }

                        renderLabel {

                            console.log(currentValue, currentValue == onValue, currentValue == offValue)

                            when (currentValue) {
                                onValue -> offValue
                                else -> onValue
                            }?.let {
                                setValue(it)
                            }
                        }
                    }
                }

                renderErrors(this)
            }
        }
    }
}
