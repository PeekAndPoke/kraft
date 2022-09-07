package de.peekandpoke.kraft.addons.pdfjs

import de.peekandpoke.kraft.addons.pdfjs.js.PdfjsLib
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.w3c.dom.HTMLScriptElement
import org.w3c.dom.get
import kotlin.js.Promise

class PdfJs private constructor(val lib: PdfjsLib) {

    companion object {
        private var instance: Promise<PdfJs>? = null
        private var scriptTag: HTMLScriptElement? = null

        var librarySource: LibrarySrc = LibrarySrc.CdnJs.v2_16_105

        suspend fun instance(): PdfJs {

            instance?.let { return it.await() }

            val libSrc = librarySource.src
            val libWorkerSrc = librarySource.workerSrc

            instance = Promise { resolve, _ ->

                scriptTag = (document.createElement("script") as HTMLScriptElement).also {
                    it.src = libSrc
                    it.type = "text/javascript"
                    it.onload = { _ ->
                        @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
                        val lib: PdfjsLib = window.asDynamic().pdfjsLib as PdfjsLib

                        lib.GlobalWorkerOptions.workerSrc = libWorkerSrc

                        resolve(PdfJs(lib))
                    }

                    document.getElementsByTagName("head")[0]?.appendChild(it)
                }
            }

            return instance!!.await()
        }

        fun getDocument(src: String): Flow<PdfjsLib.PDFDocumentProxy> {
            return flow {
                emit(
                    instance().lib.getDocument(src).promise.await()
                )
            }
        }

        fun getDocument(src: PdfjsLib.GetDocumentParameters): Flow<PdfjsLib.PDFDocumentProxy> {
            return flow {
                emit(
                    instance().lib.getDocument(src).promise.await()
                )
            }
        }
    }

    interface LibrarySrc {
        val src: String
        val workerSrc: String

        /**
         * https://cdnjs.com/libraries/pdf.js
         */
        data class CdnJs(
            override val src: String,
            override val workerSrc: String,
        ) : LibrarySrc {

            companion object {
                val v2_16_105
                    get() = CdnJs(
                        src = "https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.16.105/pdf.min.js",
                        workerSrc = "https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.16.105/pdf.worker.min.js",
                    )
            }
        }
    }

}
