package de.peekandpoke.kraft.addons.semanticui.pagination

import de.peekandpoke.kraft.components.key
import de.peekandpoke.ultra.common.model.Paged
import de.peekandpoke.ultra.common.model.search.PagedSearchFilter
import de.peekandpoke.ultra.semanticui.ui
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
                Pagination(
                    activePage = filter.page,
                    totalPages = paged.fullPageCount
                ) { onChange(filter.copy(page = it)) }
            }
            ui.item {
                ElementsPerPage(epp = filter.epp) { onChange(filter.copy(epp = it)) }
            }
        }
    }
}
