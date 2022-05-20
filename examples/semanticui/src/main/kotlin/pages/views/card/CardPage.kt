package de.peekandpoke.kraft.examples.semanticui.pages.views.card

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.examples.semanticui.helpers.ExampleWithCode
import de.peekandpoke.kraft.examples.semanticui.helpers.readTheDocs
import de.peekandpoke.kraft.semanticui.icon
import de.peekandpoke.kraft.semanticui.noui
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
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

            renderSingleCard()
        }
    }

    private fun FlowContent.renderSingleCard() {
        ui.header H2 { +"Card" }

        p {
            +"A single card"
        }

        ExampleWithCode(
            """
                ui.card {
                    noui.image {
                        img(src = "/images/avatar2/large/kristy.png")
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
            """.trimIndent()
        ) {
            ui.card {
                noui.image {
                    img(src = "/images/avatar2/large/kristy.png")
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
        }

        ExampleWithCode(
            """
                ui.card {
                    noui.content {
                        noui.right.floated.meta { +"14h" }
                        ui.avatar.image Img {
                            src = "/images/avatar2/large/elliot.jpg"
                            +"Elliot"
                        }
                    }
                    noui.image {
                        img(src = "/images/wireframe/image.png")
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
            """.trimIndent()
        ) {
            ui.card {
                noui.content {
                    noui.right.floated.meta { +"14h" }
                    ui.avatar.image Img {
                        src = "/images/avatar2/large/elliot.jpg"
                        +"Elliot"
                    }
                }
                noui.image {
                    img(src = "/images/wireframe/image.png")
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
        }
    }
}
