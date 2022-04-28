package de.peekandpoke.kraft.addons.forms.validation.collections

import de.peekandpoke.kraft.addons.forms.validation.FormRuleMarker
import de.peekandpoke.kraft.addons.forms.validation.GenericRule
import de.peekandpoke.kraft.addons.forms.validation.Rule

@FormRuleMarker
fun <T : Collection<*>> notEmpty(message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { it.isNotEmpty() }
)

@FormRuleMarker
fun <T : Collection<*>> notEmpty(message: String = "Must not be empty"): Rule<T> =
    notEmpty { message }

@FormRuleMarker
fun <T : Collection<*>> minCount(count: Int, message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { it.size >= count }
)

@FormRuleMarker
fun <T : Collection<*>> minCount(count: Int, message: String = "Must have at least $count items"): Rule<T> =
    minCount(count) { message }

@FormRuleMarker
fun <T : Collection<*>> maxCount(count: Int, message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { it.size >= count }
)

@FormRuleMarker
fun <T : Collection<*>> maxCount(count: Int, message: String = "Must have at most $count items"): Rule<T> =
    maxCount(count) { message }

@FormRuleMarker
fun <T : Collection<*>> exactCount(count: Int, message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { it.size == count }
)

@FormRuleMarker
fun <T : Collection<*>> exactCount(count: Int, message: String = "Must have $count items"): Rule<T> =
    maxCount(count) { message }
