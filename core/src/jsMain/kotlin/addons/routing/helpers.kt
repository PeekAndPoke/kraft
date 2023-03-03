package de.peekandpoke.kraft.addons.routing

import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.streams.addons.mapAsync
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun navigateTo(uri: String) {

    val current = window.location.href

    val loc = current.split("#")
    val base = loc[0]
    val new = "$base#${uri.trimStart('#')}"

    if (current != new) {
        window.location.href = new
    }
}

fun <C> Component<C>.urlParam(
    router: Router, name: String, default: Int, onChange: ((Int) -> Unit)? = null,
) = urlParams(
    router = router,
    fromParams = { it[name]?.toIntOrNull() ?: default },
    toParams = { mapOf(name to "$it") },
    onChange = onChange,
)

fun <C> Component<C>.urlParam(
    router: Router, name: String, default: String, onChange: ((String) -> Unit)? = null,
) = urlParams(
    router = router,
    fromParams = { it[name] ?: default },
    toParams = { mapOf(name to it) },
    onChange = onChange,
)

fun <C, T> Component<C>.urlParams(
    router: Router,
    fromParams: (Map<String, String>) -> T,
    toParams: (T) -> Map<String, Any?>,
    onChange: ((T) -> Unit)? = null,
): ReadWriteProperty<Component<C>, T> {

    var current: T = fromParams(router.current().matchedRoute.allParams)

    // When the URI changes we modify the object and send it through the stream
    router.current.mapAsync { it.also { delay(10) } }.subscribe {

        it?.let { next ->
            current = fromParams(next.matchedRoute.allParams)
//            console.log("Next params", current)
            onChange?.invoke(current)
        }
    }

    return object : ReadWriteProperty<Component<C>, T> {

        override fun getValue(thisRef: Component<C>, property: KProperty<*>): T {
            return current
        }

        override fun setValue(thisRef: Component<C>, property: KProperty<*>, value: T) {
            // remember the next value
            current = value

            // Modify the params in the route
            val params = toParams(value)
                .mapValues { (_, v) ->
                    when (v) {
                        null -> ""
                        is Iterable<*> -> v.joinToString(",")
                        else -> v.toString()
                    }
                }

            // We will remove empty parameters from the url
            val toBeRemoved = params
                .filter { (_, v) -> v.isBlank() }
                .map { (n, _) -> n }

            // We will also keep additional parameters on the url
            val currentRoute = router.current().matchedRoute
            val currentParams = currentRoute.queryParams
            // Merge the currentParams with the incoming params
            val updatedParams = currentParams.plus(params)
                // And remove the empty ones
                .filter { (n, _) -> n !in toBeRemoved }

            // Keep the current value
            current = fromParams(updatedParams)

            router.replaceUri(route = currentRoute.withQueryParams(updatedParams))

            triggerRedraw()
        }
    }
}
