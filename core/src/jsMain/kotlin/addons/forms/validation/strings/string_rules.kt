package de.peekandpoke.kraft.addons.forms.validation.strings

import de.peekandpoke.kraft.addons.forms.validation.FormRuleMarker
import de.peekandpoke.kraft.addons.forms.validation.GenericRule
import de.peekandpoke.kraft.addons.forms.validation.Rule
import de.peekandpoke.ultra.common.isEmail

@FormRuleMarker
fun <T : CharSequence> empty(message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { it.isEmpty() }
)

@FormRuleMarker
fun <T : CharSequence> empty(message: String = "Must be empty"): Rule<T> =
    empty { message }

@FormRuleMarker
fun <T : CharSequence> notEmpty(message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { it.isNotEmpty() }
)

@FormRuleMarker
fun <T : CharSequence> notEmpty(message: String = "Must not be empty"): Rule<T> =
    notEmpty { message }

@FormRuleMarker
fun <T : CharSequence> blank(message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { it.isBlank() }
)

@FormRuleMarker
fun <T : CharSequence> blank(message: String = "Must be blank"): Rule<T> =
    blank { message }

@FormRuleMarker
fun <T : CharSequence> notBlank(message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { it.isNotBlank() }
)

@FormRuleMarker
fun <T : CharSequence> notBlank(message: String = "Must not be blank"): Rule<T> =
    notBlank { message }

@FormRuleMarker
fun <T : CharSequence> validEmail(message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { it.isNotBlank() && it.toString().isEmail() }
)

@FormRuleMarker
fun <T : CharSequence> validEmail(message: String = "Must be a valid email"): Rule<T> =
    validEmail { message }

@FormRuleMarker
fun <T : CharSequence> minLength(length: Int, message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { it.length >= length }
)

@FormRuleMarker
fun <T : CharSequence> minLength(length: Int, message: String = "Must be at least $length characters"): Rule<T> =
    minLength(length) { message }

@FormRuleMarker
fun <T : CharSequence> maxLength(length: Int, message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { it.length <= length }
)

@FormRuleMarker
fun <T : CharSequence> maxLength(length: Int, message: String = "Must be at most $length characters"): Rule<T> =
    maxLength(length) { message }

@FormRuleMarker
fun <T : CharSequence> exactLength(length: Int, message: (T) -> String): Rule<T> = GenericRule(
    messageFn = message,
    checkFn = { it.length == length }
)

@FormRuleMarker
fun <T : CharSequence> exactLength(length: Int, message: String = "Must be $length characters"): Rule<T> =
    exactLength(length) { message }
