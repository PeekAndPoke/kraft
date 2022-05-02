package de.peekandpoke.kraft.addons.pagination

import de.peekandpoke.kraft.addons.routing.Router
import de.peekandpoke.kraft.addons.routing.urlParams
import de.peekandpoke.kraft.components.Component
import de.peekandpoke.ultra.common.model.search.PagedSearchFilter
import kotlin.properties.ReadWriteProperty

fun <C> Component<C>.pagedSearchFilter(
    router: Router,
    onChange: ((PagedSearchFilter) -> Unit)? = null,
): ReadWriteProperty<Component<C>, PagedSearchFilter> = urlParams(
    router = router,
    fromParams = {
        PagedSearchFilter(
            search = it["search"] ?: "",
            page = it["page"]?.toIntOrNull() ?: 1,
            epp = it["epp"]?.toIntOrNull() ?: 20,
        )
    },
    toParams = {
        mapOf(
            "search" to it.search,
            "page" to it.page,
            "epp" to it.epp
        )
    },
    onChange = onChange,
)
