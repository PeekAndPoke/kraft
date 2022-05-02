package de.peekandpoke.kraft.addons.semanticui.forms

import addons.forms.SettingsBuilder
import de.peekandpoke.kraft.addons.forms.GenericFormField
import de.peekandpoke.kraft.addons.forms.KraftFormsDsl
import de.peekandpoke.kraft.components.ComponentRef
import de.peekandpoke.kraft.utils.stringToString
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.semanticui.SemanticFn
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.html.InputType
import kotlinx.html.Tag
import kotlinx.html.input
import kotlin.reflect.KMutableProperty0

@KraftFormsDsl
val Tag.UiInputField get() = UiInputFieldDef.InputRenderer(this)

@KraftFormsDsl
val Tag.UiPasswordField get() = UiInputFieldDef.PasswordRenderer(this)

@KraftFormsDsl
val Tag.UiDateField get() = UiInputFieldDef.DateRenderer(this)

@KraftFormsDsl
val Tag.UiDateTimeField get() = UiInputFieldDef.DateTimeRenderer(this)

@KraftFormsDsl
val Tag.UiTimeField get() = UiInputFieldDef.TimeRenderer(this)

/**
 * Semantic ui input field definition
 */
object UiInputFieldDef : GenericFormField.Definition {

    class InputRenderer(tag: Tag) : GenericFormField.Renderer(tag, UiInputFieldDef),
        GenericFormField.Renderer.ForStrings,
        GenericFormField.Renderer.ForNumbers

    class PasswordRenderer(tag: Tag) : GenericFormField.Renderer(tag, UiInputFieldDef) {
        /**
         * Renders the field for a String
         */
        @KraftFormsDsl
        operator fun invoke(
            prop: KMutableProperty0<String>,
            builder: SettingsBuilder<String>,
        ): ComponentRef<GenericFormField<String>> = invoke(prop(), prop::set, builder)

        /**
         * Renders the field for a String
         */
        @KraftFormsDsl
        operator fun invoke(
            value: String,
            onChange: (String) -> Unit,
            builder: SettingsBuilder<String>,
        ): ComponentRef<GenericFormField<String>> = def.run {
            tag.render(value, onChange, ::stringToString, ::stringToString) {
                input.type(InputType.password)
                builder()
            }
        }
    }

    class DateRenderer(tag: Tag) : GenericFormField.Renderer(tag, UiInputFieldDef),
        GenericFormField.Renderer.ForStrings,
        GenericFormField.Renderer.ForDates

    class DateTimeRenderer(tag: Tag) : GenericFormField.Renderer(tag, UiInputFieldDef),
        GenericFormField.Renderer.ForStrings,
        GenericFormField.Renderer.ForDateTimes

    class TimeRenderer(tag: Tag) : GenericFormField.Renderer(tag, UiInputFieldDef),
        GenericFormField.Renderer.ForStrings,
        GenericFormField.Renderer.ForTimes

    override fun <T> GenericFormField<T>.content(vdom: VDom) {
        with(vdom) {

            val appear: SemanticFn = settings.semantic.appear

            ui.appear().given(hasErrors) { error }.field {

                renderLabel("input")

                input {
                    applyAll()
                }

                renderErrors(this)
            }
        }
    }
}
