package de.peekandpoke.kraft.addons.forms.validation

class GenericRule<T>(
    private val messageFn: (value: T) -> String,
    private val checkFn: (value: T) -> Boolean
) : Rule<T> {
    override fun check(value: T): Boolean {
        return checkFn(value)
    }

    override fun getMessage(value: T): String {
        return messageFn(value)
    }
}
