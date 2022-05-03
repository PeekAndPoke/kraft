package de.peekandpoke.kraft.addons.semanticui.forms

import de.peekandpoke.kraft.addons.forms.FieldOptions
import de.peekandpoke.kraft.addons.forms.KraftFormsSettingDsl
import de.peekandpoke.ultra.common.TypedKey
import de.peekandpoke.ultra.semanticui.SemanticFn

interface SemanticOptions<T> : FieldOptions<T> {

    companion object {
        val appearKey = TypedKey<SemanticFn>("appear")
    }

    interface Checkbox<T> : FieldOptions<T> {

        companion object {
            val styleKey = TypedKey<SemanticFn>("style")
        }

        @KraftFormsSettingDsl
        val style get() = access(styleKey)

        @KraftFormsSettingDsl
        fun toggle() {
            style { toggle }
        }

        @KraftFormsSettingDsl
        fun slider() {
            style { slider }
        }
    }

    @KraftFormsSettingDsl
    val appear get() = access(appearKey)
}

