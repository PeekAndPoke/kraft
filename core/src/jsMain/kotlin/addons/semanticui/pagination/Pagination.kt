package de.peekandpoke.kraft.addons.semanticui.pagination

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
fun Tag.Pagination(
    activePage: Int,
    totalPages: Number?,
    style: Pagination.Style = Pagination.Style.default,
    onChange: (Int) -> Unit,
) = comp(
    Pagination.Props(
        activePage = activePage,
        totalPages = totalPages,
        style = style,
        onChange = onChange,
    )
) {
    Pagination(it)
}

class Pagination(ctx: Ctx<Props>) : Component<Pagination.Props>(ctx) {

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

        ui.pagination.menu {

            noui.item A {
                icon.angle_double_left()
                onClick {
                    props.onChange(1)
                }
            }

            if (firstEntry > 1) {
                noui.item { +"..." }
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
                noui.item { +"..." }
            }

            noui.item A {
                icon.angle_double_right()
                onClick {
                    props.onChange(lastPage)
                }
            }
        }
    }
}
