package de.peekandpoke.kraft.addons.forms.validation

@FormRuleMarker
fun <T> nonNull(message: String = "Must not be empty"): Rule<T> = GenericRule(
    messageFn = { message },
    checkFn = { it != null },
)

@FormRuleMarker
fun <T> equalTo(compareWith: () -> T, message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { it == compareWith() }
)

@FormRuleMarker
fun <T> equalTo(compareWith: () -> T, message: String = "Must be equal to '$compareWith()'"): Rule<T> =
    equalTo(compareWith) { message }

@FormRuleMarker
fun <T> equalTo(compareWith: T, message: String = "Must be equal to '$compareWith()'"): Rule<T> =
    equalTo({ compareWith }) { message }

@FormRuleMarker
fun <T> notEqualTo(
    compareWith: () -> T,
    message: (T) -> String = { "Must not be equal to '$compareWith()'" },
) = GenericRule(
    messageFn = message,
    checkFn = { it != compareWith() }
)

@FormRuleMarker
fun <T> notEqualTo(compareWith: () -> T, message: String = "Must not be equal to '$compareWith()'"): Rule<T> =
    notEqualTo(compareWith) { message }

@FormRuleMarker
fun <T> notEqualTo(compareWith: T, message: String = "Must not be equal to '$compareWith()'"): Rule<T> =
    notEqualTo({ compareWith }) { message }

@FormRuleMarker
fun <T> anyOf(values: () -> Collection<T>, message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { it in values() },
)

@FormRuleMarker
fun <T> anyOf(values: Collection<T>, message: String = "Must be a valid input"): Rule<T> =
    anyOf({ values }) { message }

@FormRuleMarker
fun <T> noneOf(values: () -> Collection<T>, message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { it !in values() },
)

@FormRuleMarker
fun <T> noneOf(values: () -> Collection<T>, message: String = "Must be a valid input"): Rule<T> =
    noneOf(values) { message }

@FormRuleMarker
fun <T> noneOf(values: Collection<T>, message: String = "Must be a valid input"): Rule<T> =
    noneOf({ values }) { message }

@FormRuleMarker
fun <T> given(
    check: (T) -> Boolean,
    message: (T) -> String = { "Must be a valid input" },
): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = check,
)
