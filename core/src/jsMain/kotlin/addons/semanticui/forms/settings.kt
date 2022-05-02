package de.peekandpoke.kraft.addons.semanticui.forms

import addons.forms.Settings
import addons.forms.SettingsBase
import de.peekandpoke.kraft.addons.forms.KraftFormsSettingDsl
import de.peekandpoke.ultra.common.TypedKey
import de.peekandpoke.ultra.semanticui.SemanticFn

private val semanticSettings = TypedKey<SemanticSettings<*>>("semantic-settings")

@KraftFormsSettingDsl
val <T> Settings<T>.semantic: SemanticSettings<T>
    get() {
        @Suppress("UNCHECKED_CAST")
        return getOrPut(semanticSettings) { SemanticSettings<T>() } as SemanticSettings<T>
    }

class SemanticSettings<T> : SettingsBase<T>() {

    class TextArea<T> : SettingsBase<T>() {

        var verticalAutoResize: Boolean = true
            private set

        @KraftFormsSettingDsl
        fun verticalAutoResize(enabled: Boolean) {
            verticalAutoResize = enabled
        }
    }

    class Checkbox<T> : SettingsBase<T>() {

        @KraftFormsSettingDsl
        var style: SemanticFn = { this }
            private set

        @KraftFormsSettingDsl
        var options: Pair<T, T>? = null
            private set

        @KraftFormsSettingDsl
        var verticalAutoResize: Boolean = true
            private set

        @KraftFormsSettingDsl
        fun toggle() = apply {
            style = { toggle }
        }

        @KraftFormsSettingDsl
        fun slider() = apply {
            style = { slider }
        }

        @KraftFormsSettingDsl
        fun options(off: T, on: T) {
            options = off to on
        }
    }

    val textArea = TextArea<T>()
    val checkbox = Checkbox<T>()

    @KraftFormsSettingDsl
    var appear: SemanticFn = { this }
        private set

    @KraftFormsSettingDsl
    fun appear(appear: SemanticFn) = apply {
        this.appear = appear
    }
}

