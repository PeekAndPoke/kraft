package de.peekandpoke.kraft.addons.forms

import de.peekandpoke.kraft.components.Component

@DslMarker
annotation class KraftFormsDsl

@DslMarker
annotation class KraftFormsSettingDsl

@KraftFormsDsl
fun Component<*>.formController(): FormController {
    return FormController(this)
}

