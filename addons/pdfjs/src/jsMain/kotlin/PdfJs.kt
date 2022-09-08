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

        private suspend fun instance(): PdfJs {

            instance?.let { return it.await() }

            val source = librarySource

            instance = Promise { resolve, _ ->

                scriptTag = (document.createElement("script") as HTMLScriptElement).also {
                    it.src = source.src
                    it.type = "text/javascript"
                    it.crossOrigin = source.crossOrigin
                    it.asDynamic().referrerPolicy = source.referrerPolicy
                    it.onload = { _ ->

                        val winDyn = window.asDynamic()

                        val candidates = sequenceOf(
                            { "window.pdfjsLib" to winDyn.pdfjsLib },
                            { "window.exports" to winDyn.exports },
                            { "window.module" to winDyn.module },
                            { "window.module?.exports" to winDyn.module?.exports }
                        )

                        val (name: String, lib: PdfjsLib) = candidates
                            .map { it() }
                            .first { (name, item) ->
                                @Suppress("UnsafeCastFromDynamic")
                                !!item && !!item.getDocument
                            }

                        console.info("pdfjsLib was loaded into $name")

                        lib.GlobalWorkerOptions.workerSrc = source.workerSrc

                        @Suppress("UnsafeCastFromDynamic")
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
        val integrity: String
        val crossOrigin: String
        val referrerPolicy: String
        val workerSrc: String

        /**
         * https://cdnjs.com/libraries/pdf.js
         */
        data class CdnJs(
            override val src: String,
            override val integrity: String,
            override val crossOrigin: String = "anonymous",
            override val referrerPolicy: String = "no-referrer",
            override val workerSrc: String,
        ) : LibrarySrc {

            companion object {
                val v2_16_105 = CdnJs(
                    src = "https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.16.105/pdf.min.js",
                    integrity = "sha512-tqaIiFJopq4lTBmFlWF0MNzzTpDsHyug8tJaaY0VkcH5AR2ANMJlcD+3fIL+RQ4JU3K6edt9OoySKfCCyKgkng==",
                    workerSrc = "https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.16.105/pdf.worker.min.js",
                )
            }
        }
    }
}
