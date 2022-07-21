package de.peekandpoke.kraft.examples.fomanticui.pages.views.card

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.examples.fomanticui.helpers.HorizontalContentAndCode
import de.peekandpoke.kraft.examples.fomanticui.helpers.example
import de.peekandpoke.kraft.examples.fomanticui.helpers.readTheDocs
import de.peekandpoke.kraft.semanticui.icon
import de.peekandpoke.kraft.semanticui.noui
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import generated.ExtractedCodeBlocks
import kotlinx.html.*

@Suppress("FunctionName")
fun Tag.CardPage() = comp {
    CardPage(it)
}

class CardPage(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        ui.basic.segment {
            ui.dividing.header H1 { +"Card" }

            readTheDocs("https://fomantic-ui.com/views/card.html")

            ui.header H2 { +"Card" }

            renderSingleCard()
            renderSingleCard2()
        }
    }

    private fun FlowContent.renderSingleCard() = example {
        ui.header { +"A single card" }

        HorizontalContentAndCode(
            ExtractedCodeBlocks.pages_views_card_CardPage_kt_renderSingleCard,
        ) {
            // <CodeBlock renderSingleCard>
            ui.card {
                noui.image {
                    img(src = "images/avatar2/large/kristy.png")
                }
                noui.content {
                    noui.header A { +"Kristy" }
                    noui.meta {
                        +"Joined in 2022"
                    }
                    noui.description {
                        +"Kristy is an art director living in Leipzig."
                    }
                }
                noui.extra.content {
                    a {
                        icon.user()
                        +"22 Friends"
                    }
                }
            }
            // </CodeBlock>
        }
    }

    private fun FlowContent.renderSingleCard2() = example {
        HorizontalContentAndCode(
            ExtractedCodeBlocks.pages_views_card_CardPage_kt_renderSingleCard2,
        ) {
            // <CodeBlock renderSingleCard2>
            ui.card {
                noui.content {
                    noui.right.floated.meta { +"14h" }
                    ui.avatar.image Img {
                        src = "images/avatar2/large/elliot.jpg"
                        +"Elliot"
                    }
                }
                noui.image {
                    img(src = "images/wireframe/image.png")
                }
                noui.content {
                    noui.right.floated Span {
                        icon.heart_outline()
                        +"17 Likes"
                    }
                    icon.comment()
                    +"3 comments"
                }
                noui.extra.content {
                    ui.large.with("transparent").left.icon.input {
                        icon.heart_outline()
                        input {
                            type = InputType.text
                            placeholder = "Add comment..."
                        }
                    }
                }
            }
            // </CodeBlock>
        }
    }
}
