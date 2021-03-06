package de.peekandpoke.kraft.addons.routing

import de.peekandpoke.kraft.streams.Stream
import de.peekandpoke.kraft.streams.StreamSource
import kotlinx.browser.window
import org.w3c.dom.events.Event

/**
 * The Router
 */
class Router(private val mountedRoutes: List<MountedRoute>, private var enabled: Boolean) {

    data class History(
        val router: Router,
        val entries: List<ActiveRoute>
    ) {
        val canGoBack: Boolean = entries.size > 1

        fun navBack() {
            router.navBack()
        }
    }

    private val prefix = "#"

    /** Writable stream with the current [ActiveRoute] */
    private val _current = StreamSource(ActiveRoute("", Route.Match.default, MountedRoute.default))

    /** Public readonly stream with the current [ActiveRoute] */
    val current: Stream<ActiveRoute> = _current

    private val _historyEntries: MutableList<ActiveRoute> = mutableListOf()

    private val _history = StreamSource(History(this, _historyEntries.toList()))
    val history: Stream<History> = _history

    init {
        window.addEventListener("hashchange", ::windowListener)

        current.subscribeToStream {
            _historyEntries.add(it)
            _history(History(this, _historyEntries.toList()))
        }
    }

    fun disable() {
        enabled = false
    }

    fun enable() {
        enabled = true
    }

    /**
     * Navigates to the given uri.
     */
    fun navToUri(uri: String) {
        if (window.location.hash != uri) {
            window.location.hash = uri
        } else {
            resolveCurrentRoute()
        }
    }

    /**
     * Navigates to the [route] by filling in the given [routeParams] and [queryParams].
     */
    fun navToUri(route: Route, routeParams: Map<String, String>, queryParams: Map<String, String>) {
        navToUri(
            route.buildUri(routeParams = routeParams, queryParams = queryParams)
        )
    }

    /**
     * Navigates to the [route].
     */
    fun navToUri(route: Route.Match) {
        navToUri(route = route.route, routeParams = route.routeParams, queryParams = route.queryParams)
    }

    /**
     * Replaces the current uri with changing the history
     */
    fun replaceUri(uri: String) {

        if (window.location.hash != uri) {
            window.history.replaceState(
                null,
                "",
                window.location.pathname + uri
            )
            // Remove the last from the history
            _historyEntries.removeLast()
            // Resolve the next route
            resolveCurrentRoute()
        }
    }

    /**
     * Replaces the current uri without changing the browser history.
     */
    fun replaceUri(route: Route, routeParams: Map<String, String>, queryParams: Map<String, String>) {
        replaceUri(
            route.buildUri(routeParams = routeParams, queryParams = queryParams)
        )
    }

    /**
     * Replaces the current uri without changing the browser history.
     */
    fun replaceUri(route: Route.Match) {
        replaceUri(
            route.route, routeParams = route.routeParams, queryParams = route.queryParams
        )
    }

    /**
     * Navigates to the given uri while clearing the navigation history.
     */
    fun clearHistoryAndNavTo(uri: String) {
        _historyEntries.clear()
        navToUri(uri)
    }

    /**
     * Navigates to the previous route, when this is possible.
     */
    fun navBack() {
        if (history().canGoBack) {
            // remove the current active route
            _historyEntries.removeLast()
            // remove the previous active route and go back to it
            navToUri(_historyEntries.removeLast().uri)
        }
    }

    /**
     * Tries to resolve the next [ActiveRoute] by looking at the browser current location.
     *
     * If a route is resolved then the [current] stream will be updated.
     */
    fun resolveCurrentRoute() {
        // get the location from the browser
        val location = window.location.hash.removePrefix(prefix)

        // Go through all routes and try to find the first one that matches the current location
        val resolved = mountedRoutes.asSequence()
            // Find matches
            .map { mounted -> mounted.route.match(location)?.let { mounted to it } }
            // Skip all that did not match
            .filterNotNull()
            // Get the first match, if there is any
            .firstOrNull()

        if (resolved == null) {
            console.error("Could not resolve route: $location")
        }

        resolved?.let { (mounted, match) ->
            // Create a context for the router middlewares
            val ctx = RouterMiddlewareContext(this, match.route)
            // Call all the middlewares
            mounted.middlewares.forEach { it(ctx) }

            // Do we have a redirect ?
            when (val redirect = ctx.redirectToUri) {
                null -> {
                    // Notify all subscribers that the active route has changed
                    _current(ActiveRoute(location, match, mounted))
                }
                else -> {
                    // Yes so let's go to the redirect
                    navToUri(redirect)
                }
            }
        }
    }

    /**
     * Private listener for the "hashchange" event
     */
    private fun windowListener(event: Event) {
        if (enabled) {
            event.preventDefault()

            resolveCurrentRoute()
        }
    }
}
