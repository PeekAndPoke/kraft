package de.peekandpoke.kraft.addons.semanticui.PaginationPages

import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.components.Ctx
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.onClick
import de.peekandpoke.kraft.semanticui.icon
import de.peekandpoke.kraft.semanticui.noui
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.Tag
import kotlin.math.max
import kotlin.math.min

@Suppress("FunctionName")
fun Tag.PaginationPages(
    activePage: Int,
    totalPages: Number?,
    style: PaginationPages.Style = PaginationPages.Style.default,
    onChange: (Int) -> Unit,
) = comp(
    PaginationPages.Props(
        activePage = activePage,
        totalPages = totalPages,
        style = style,
        onChange = onChange,
    )
) {
    PaginationPages(it)
}

class PaginationPages(ctx: Ctx<Props>) : Component<PaginationPages.Props>(ctx) {

    ////  PROPS  //////////////////////////////////////////////////////////////////////////////////////////////////

    data class Props(
        val activePage: Int,
        val totalPages: Number?,
        val style: Style,
        val onChange: (Int) -> Unit,
    )

    data class Style(
        val groupSize: Int,
    ) {
        companion object {
            val default = Style(groupSize = 5)
        }
    }

    ////  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    ////  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {

        val groupSize = props.style.groupSize
        val groupSizeHalf = props.style.groupSize / 2

        val lastPage = props.totalPages?.toInt() ?: (props.activePage + 1)

        val firstEntry = max(1, props.activePage - groupSizeHalf)
        val lastEntry = min(firstEntry + groupSize - 1, lastPage)

        ui.small.pagination.menu {

            noui.icon.item A {
                onClick {
                    props.onChange(1)
                }
                icon.angle_double_left()
            }

            if (firstEntry > 1) {
                noui.icon.item {
                    icon.comment_dots()
                }
            }

            (firstEntry..lastEntry).forEach { page ->
                noui.given(props.activePage == page) { active }.item A {
                    +"$page"
                    onClick {
                        props.onChange(page)
                    }
                }
            }

            if (lastEntry < lastPage) {
                noui.icon.item {
                    icon.comment_dots()
                }
            }

            noui.icon.item A {
                onClick {
                    props.onChange(lastPage)
                }
                icon.angle_double_right()
            }
        }
    }
}
