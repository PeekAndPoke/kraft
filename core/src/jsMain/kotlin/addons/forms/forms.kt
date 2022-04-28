package de.peekandpoke.kraft.addons.forms

import de.peekandpoke.kraft.components.Component

@DslMarker
annotation class FormsDslMarker

fun Component<*>.formController(): FormController {
    return FormController(this)
}
