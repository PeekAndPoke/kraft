package de.peekandpoke.kraft.examples.jsaddons.pdfjs

import de.peekandpoke.kraft.addons.pdfjs.js.pdfjsLib
import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.components.onClick
import de.peekandpoke.kraft.semanticui.icon
import de.peekandpoke.kraft.semanticui.noui
import de.peekandpoke.kraft.semanticui.ui
import de.peekandpoke.kraft.utils.jsObject
import de.peekandpoke.kraft.utils.launch
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.coroutines.await
import kotlinx.html.Tag
import kotlinx.html.canvas
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

@Suppress("FunctionName")
fun Tag.PdfJsExample() = comp {
    PdfJsExample(it)
}

class PdfJsExample(ctx: NoProps) : PureComponent(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    // https://pspdfkit.com/blog/2021/how-to-build-a-jquery-pdf-viewer-with-pdfjs/

    private var settingPage by value(1) {
        renderPage()
    }

    private var settingScale by value(1.0) {
        renderPage()
    }

    private var doc: pdfjsLib.PDFDocumentProxy? by value(null)

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    init {
        launch {
            // https://stackoverflow.com/questions/58728223/how-to-import-pdfjs-dist-in-angular-project-in-order-to-know-to-view-port-of-the
            pdfjsLib.GlobalWorkerOptions.workerSrc =
                "//cdnjs.cloudflare.com/ajax/libs/pdf.js/2.16.105/pdf.worker.min.js"

            doc = pdfjsLib.getDocument("pdf/eg14_cats_and_people.pdf")
                .promise
                .await()

            console.log("Loaded doc", doc)

            renderPage()
        }
    }

    private fun renderPage() {
        doc?.let { doc ->
            launch {
                val page = doc.getPage(settingPage).await()
                val viewport = page.getViewport(jsObject {
                    this.scale = settingScale
                })

                console.log("Got page", page, viewport)

                val canvas = dom!!.querySelector("canvas") as HTMLCanvasElement
                val context = canvas.getContext("2d") as CanvasRenderingContext2D

                canvas.width = viewport.width
                canvas.height = viewport.height

                page.render(jsObject {
                    this.canvasContext = context
                    this.viewport = viewport
                }).promise.await()

                console.log("rendered page")
            }
        }
    }

    override fun VDom.render() {
        ui.segment {
            ui.header H2 { +"PDF JS" }

            ui.segment {

                val numPages = doc?.numPages ?: 1

                ui.horizontal.list {
                    noui.item {
                        ui.icon.button {
                            onClick {
                                settingPage = maxOf(1, settingPage - 1)
                            }
                            icon.minus()
                        }
                    }

                    noui.item {
                        +"$settingPage / $numPages"
                    }

                    noui.item {
                        ui.icon.button {
                            onClick {
                                settingPage = minOf(numPages, settingPage + 1)
                            }
                            icon.plus()
                        }
                    }

                    noui.item {
                        ui.icon.button {
                            onClick {
                                settingScale -= 0.1
                            }
                            icon.search_minus()
                        }
                    }

                    noui.item {
                        ui.icon.button {
                            onClick {
                                settingScale += 0.1
                            }
                            icon.search_plus()
                        }
                    }
                }
            }

            canvas {


            }
        }
    }
}
