package de.peekandpoke.kraft.examples.jsaddons.pdfjs

import de.peekandpoke.kraft.addons.pdfjs.PagedPdfViewer
import de.peekandpoke.kraft.addons.pdfjs.PdfSource
import de.peekandpoke.kraft.addons.styling.css
import de.peekandpoke.kraft.components.*
import de.peekandpoke.kraft.semanticui.icon
import de.peekandpoke.kraft.semanticui.noui
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.css.Color
import kotlinx.css.backgroundColor
import kotlinx.css.padding
import kotlinx.css.px
import kotlinx.html.Tag

@Suppress("FunctionName")
fun Tag.PdfJsExample() = comp {
    PdfJsExample(it)
}

class PdfJsExample(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    // https://pspdfkit.com/blog/2021/how-to-build-a-jquery-pdf-viewer-with-pdfjs/

    private var pagedPdfViewer = ComponentRef.Tracker<PagedPdfViewer>()

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        ui.segment {
            ui.header H2 { +"PDF JS" }

            ui.top.attached.center.aligned.segment {

                ui.horizontal.list {
                    noui.item {
                        ui.icon.button {
                            onClick {
                                pagedPdfViewer {
                                    it.gotoPreviousPage()
                                }
                            }
                            icon.minus()
                        }
                    }

                    noui.item {
                        pagedPdfViewer {
                            it.getNumPages()?.let { numPages ->
                                +"${it.getCurrentPage()} / $numPages"
                            }
                        }
                    }

                    noui.item {
                        ui.icon.button {
                            onClick {
                                pagedPdfViewer {
                                    it.gotoNextPage()
                                }
                            }
                            icon.plus()
                        }
                    }

                    noui.item {
                        ui.icon.button {
                            onClick {
                                pagedPdfViewer {
                                    it.modifyScale { scale -> scale - 0.2 }
                                }
                            }
                            icon.search_minus()
                        }
                    }

                    noui.item {
                        ui.icon.button {
                            onClick {
                                pagedPdfViewer {
                                    it.modifyScale { scale -> scale + 0.2 }
                                }
                            }
                            icon.search_plus()
                        }
                    }
                }
            }

            ui.bottom.attached.segment {
                css {
                    padding(10.px)
                    backgroundColor = Color("#F8F8F8")
                }

                PagedPdfViewer(
                    src = PdfSource.Url("pdf/eg14_cats_and_people.pdf"),
                    options = PagedPdfViewer.Options(
                        maxHeightLandscapeVh = 70,
                        maxHeightPortraitVh = 90,
                    )
                ) { triggerRedraw() }.track(pagedPdfViewer)
            }
        }
    }
}
