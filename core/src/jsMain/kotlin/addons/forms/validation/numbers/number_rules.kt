package de.peekandpoke.kraft.addons.forms.validation.numbers

import de.peekandpoke.kraft.addons.forms.validation.FormRuleMarker
import de.peekandpoke.kraft.addons.forms.validation.GenericRule
import de.peekandpoke.kraft.addons.forms.validation.Rule

@FormRuleMarker
fun <T : Number> inRange(from: Number, to: Number, message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { from.toDouble() <= it.toDouble() && it.toDouble() <= to.toDouble() }
)

@FormRuleMarker
fun <T : Number> inRange(from: Number, to: Number, message: String = "Must be in range $from .. $to"): Rule<T> =
    inRange(from, to) { message }

@FormRuleMarker
fun <T : Number> greaterThan(value: Number, message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { value.toDouble() < it.toDouble() }
)

@FormRuleMarker
fun <T : Number> greaterThan(value: Number, message: String = "Must be greater than $value"): Rule<T> =
    greaterThanOrEqual(value) { message }

@FormRuleMarker
fun <T : Number> greaterThanOrEqual(value: Number, message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { value.toDouble() <= it.toDouble() }
)

@FormRuleMarker
fun <T : Number> greaterThanOrEqual(value: Number, message: String = "Must be greater than $value or equal"): Rule<T> =
    greaterThanOrEqual(value) { message }

@FormRuleMarker
fun <T : Number> lessThan(value: Number, message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { value.toDouble() > it.toDouble() }
)

@FormRuleMarker
fun <T : Number> lessThan(value: Number, message: String = "Must be less than $value"): Rule<T> =
    lessThan(value) { message }

@FormRuleMarker
fun <T : Number> lessThenOrEqual(value: Number, message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { value.toDouble() >= it.toDouble() }
)

@FormRuleMarker
fun <T : Number> lessThenOrEqual(value: Number, message: String = "Must be less than $value or equal"): Rule<T> =
    lessThenOrEqual(value) { message }
