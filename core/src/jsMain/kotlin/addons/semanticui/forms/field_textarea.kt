package de.peekandpoke.kraft.addons.semanticui.forms

import de.peekandpoke.kraft.addons.forms.GenericFormField
import de.peekandpoke.kraft.addons.forms.KraftFormsDsl
import de.peekandpoke.kraft.addons.styling.css
import de.peekandpoke.kraft.utils.launch
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.css.Overflow
import kotlinx.css.maxHeight
import kotlinx.css.overflowY
import kotlinx.css.vh
import kotlinx.html.Tag
import kotlinx.html.textArea
import org.w3c.dom.HTMLTextAreaElement

@KraftFormsDsl
val Tag.UiTextArea get() = UiTextAreaDef.Renderer(this)

/**
 * Semantic ui input field definition
 */
object UiTextAreaDef : GenericFormField.Definition {

    class Renderer(tag: Tag) : GenericFormField.Renderer(tag, UiTextAreaDef),
        GenericFormField.Renderer.ForStrings

    override fun <T> GenericFormField<T>.content(vdom: VDom) {
        with(vdom) {

            ui.given(hasErrors) { error }.field {

                renderLabel("textarea")

                textArea {
                    css {
                        maxHeight = 50.vh
                        overflowY = Overflow.auto
                    }

                    applyAll()

                    +valueAsString()
                }

                renderErrors(this)
            }

            // Apply automatic vertical resize
            if (settings.semantic.textArea.verticalAutoResize) {
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
    }
}
