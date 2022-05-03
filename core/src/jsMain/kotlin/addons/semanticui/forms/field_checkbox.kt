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
import kotlin.reflect.KMutableProperty0

@KraftFormsDsl
val Tag.UiCheckboxField get() = UiCheckboxFieldDef.InputRenderer(this)

/**
 * Semantic ui input field definition
 */
object UiCheckboxFieldDef : GenericFormField.Definition {

    class InputRenderer(tag: Tag) : GenericFormField.Renderer(tag, UiCheckboxFieldDef) {

        /**
         * Renders the field for a Boolean
         */
        @KraftFormsDsl
        operator fun invoke(
            prop: KMutableProperty0<Boolean>,
            builder: SettingsBuilder<Boolean> = {},
        ) = invoke(prop(), prop::set, builder)

        /**
         * Renders the field for a Boolean
         */
        @KraftFormsDsl
        operator fun invoke(
            value: Boolean,
            onChange: (Boolean) -> Unit,
            builder: SettingsBuilder<Boolean> = {},
        ): ComponentRef<GenericFormField<Boolean>> = def.run {
            tag.render(value, onChange) {
                semantic.checkbox.options(off = false, on = true)
                builder()
            }
        }

        /**
         * Renders the field for an the type [T]
         */
        @KraftFormsDsl
        operator fun <T> invoke(
            value: T,
            on: T,
            off: T,
            onChange: (T) -> Unit,
            builder: SettingsBuilder<T> = {},
        ): ComponentRef<GenericFormField<T>> = def.run {
            tag.render(value, onChange) {
                semantic.checkbox.options(off = off, on = on)
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
