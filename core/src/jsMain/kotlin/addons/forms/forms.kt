package de.peekandpoke.kraft.addons.forms

import de.peekandpoke.kraft.components.Component

@DslMarker
annotation class KraftFormsSettingDsl

@DslMarker
annotation class KraftFormsDsl

@KraftFormsDsl
fun Component<*>.formController(): FormController {
    return FormController(this)
}

