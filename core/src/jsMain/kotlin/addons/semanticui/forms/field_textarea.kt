package de.peekandpoke.kraft.addons.semanticui.forms

import de.peekandpoke.kraft.addons.forms.FieldOptions
import de.peekandpoke.kraft.addons.forms.GenericFormField
import de.peekandpoke.kraft.addons.forms.KraftFormsDsl
import de.peekandpoke.kraft.addons.forms.TextAreaOptions
import de.peekandpoke.kraft.addons.semanticui.forms.UiTextAreaComponent.Options
import de.peekandpoke.kraft.addons.styling.css
import de.peekandpoke.kraft.components.Ctx
import de.peekandpoke.kraft.components.comp
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
import kotlinx.html.TEXTAREA
import kotlinx.html.Tag
import kotlinx.html.textArea
import org.w3c.dom.HTMLTextAreaElement

@Suppress("FunctionName")
@KraftFormsDsl
fun Tag.UiTextArea(
    value: String,
    onChange: (String) -> Unit,
    builder: Options.() -> Unit = {},
) = comp(
    UiTextAreaComponent.Props(
        value = value,
        onChange = onChange,
        options = Options().apply(builder),
    )
) {
    UiTextAreaComponent(it)
}

class UiTextAreaComponent(ctx: Ctx<Props>) :
    GenericFormField<String, Options, UiTextAreaComponent.Props>(ctx) {

    class Options : FieldOptions.Base<String>(), TextAreaOptions<String>, SemanticOptions<String>

    data class Props(
        override val value: String,
        override val onChange: (String) -> Unit,
        override val options: Options,
    ) : GenericFormField.Props<String, Options>

    override fun VDom.render() {
        ui.with(options.appear.getOrDefault { this }).given(hasErrors) { error }.field {

            renderLabel("textarea")

            textArea {
                css {
                    maxHeight = 50.vh
                    overflowY = Overflow.auto
                }

                applyAll()

                +currentValue
            }

            renderErrors(this)
        }

        // Apply automatic vertical resize
        if (options.verticalAutoResize.getOrDefault(true)) {
            window.requestAnimationFrame {
                launch {
                    delay(10) // NOTE: this is a bit hacky...

                    val textarea = dom
                        ?.getElementsByTagName("textarea")
                        ?.item(0) as? HTMLTextAreaElement

                    textarea?.let { t ->
                        t.style.height = "0"
                        t.style.height = "${t.scrollHeight + 6}px"
                    }
                }
            }
        }
    }

    fun TEXTAREA.applyAll() {
        track()
        applyPlaceholder()
    }

    fun TEXTAREA.track() {
        onInput {
            setValue((it.target as HTMLTextAreaElement).value)
        }
    }

    fun TEXTAREA.applyPlaceholder() {
        options.placeholder()?.takeIf { it.isNotBlank() }?.let {
            placeholder = it
        }
    }
}
