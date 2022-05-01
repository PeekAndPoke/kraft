package de.peekandpoke.kraft.addons.semanticui.forms

import addons.forms.Settings
import de.peekandpoke.kraft.addons.forms.KraftFormsSettingDsl
import de.peekandpoke.ultra.common.MutableTypedAttributes
import de.peekandpoke.ultra.common.TypedKey
import de.peekandpoke.ultra.semanticui.SemanticFn

class UiSettings {
    companion object {
        private val appearKey = TypedKey<SemanticFn>("appear")
        private val verticalAutoResizeKey = TypedKey<Boolean>("vertical-auto-resize")
    }

    val attributes = MutableTypedAttributes.empty()

    @KraftFormsSettingDsl
    val appear: SemanticFn get() = attributes[appearKey] ?: { this }

    @KraftFormsSettingDsl
    fun appear(appear: SemanticFn) = apply {
        attributes[appearKey] = appear
    }

    @KraftFormsSettingDsl
    val verticalAutoResize: Boolean get() = attributes[verticalAutoResizeKey] ?: true

    @KraftFormsSettingDsl
    fun verticalAutoResize(verticalAutoResize: Boolean) = apply {
        attributes[verticalAutoResizeKey] = verticalAutoResize
    }
}

private val uiSettings = TypedKey<UiSettings>("ui-settings")

@KraftFormsSettingDsl
val <T> Settings<T>.ui get() = attributes.getOrPut(uiSettings) { UiSettings() }
