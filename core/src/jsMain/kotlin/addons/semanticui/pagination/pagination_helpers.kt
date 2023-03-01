package de.peekandpoke.kraft.addons.semanticui.pagination

import de.peekandpoke.kraft.addons.semanticui.PaginationPages.PaginationPages
import de.peekandpoke.kraft.components.key
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.ultra.common.model.Paged
import de.peekandpoke.ultra.common.model.search.PagedSearchFilter
import kotlinx.html.FlowContent

fun <T> FlowContent.renderPaginationAsAttachedSegment(
    paged: Paged<T>,
    filter: PagedSearchFilter,
    onChange: (PagedSearchFilter) -> Unit
) {
    ui.attached.segment {
        key = "page-${filter.page}-epp-${filter.epp}"

        ui.horizontal.list {
            ui.item {
                PaginationPages(
                    activePage = filter.page,
                    totalPages = paged.fullPageCount
                ) { onChange(filter.copy(page = it)) }
            }
            ui.item {
                PaginationEpp(epp = filter.epp) { onChange(filter.copy(epp = it)) }
            }
        }
    }
}
