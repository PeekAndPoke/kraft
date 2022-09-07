package de.peekandpoke.kraft.addons.pdfjs.js

import org.w3c.dom.CanvasRenderingContext2D
import kotlin.js.Promise

@Suppress("ClassName")
@JsModule("pdfjs-dist")
@JsNonModule
external object pdfjsLib {

    object GlobalWorkerOptions {
        var workerSrc: String
    }

    interface PDFDocumentLoadingTask {
        val promise: Promise<PDFDocumentProxy>
    }

    /**
     * https://github.com/mozilla/pdf.js/blob/af6aacfc0eaa70f254cd6158e5cd9cbc23fb13cf/src/display/api.js#L709
     */
    interface PDFDocumentProxy {
        val numPages: Int

        val fingerprints: Array<String?>

        fun getPage(page: Int): Promise<PDFPageProxy>
    }

    /**
     * https://github.com/mozilla/pdf.js/blob/af6aacfc0eaa70f254cd6158e5cd9cbc23fb13cf/src/display/api.js#L1219
     */
    interface PDFPageProxy {

        interface GetViewportOptions {
            var scale: Number
        }

        interface RenderOptions {
            var canvasContext: CanvasRenderingContext2D
            var viewport: PageViewport
        }

        /** Page number of the page. First page is 1. */
        val pageNumber: Int

        fun getViewport(options: GetViewportOptions): PageViewport

        fun render(options: RenderOptions): RenderTask
    }

    /**
     * https://github.com/mozilla/pdf.js/blob/af6aacfc0eaa70f254cd6158e5cd9cbc23fb13cf/src/display/display_utils.js#L158
     */
    interface PageViewport {
        val width: Int
        val height: Int
        val scale: Number
        val rotation: Number
        val offsetX: Number
        val offsetY: Number
        val dontFlip: Boolean
    }

    /**
     * https://github.com/mozilla/pdf.js/blob/af6aacfc0eaa70f254cd6158e5cd9cbc23fb13cf/src/display/api.js#L3108
     */
    interface RenderTask {
        val promise: Promise<Unit>
    }

    fun getDocument(url: String): PDFDocumentLoadingTask
}

