package de.peekandpoke.kraft.utils

import kotlinx.browser.document
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.asDeferred
import org.w3c.dom.HTMLScriptElement
import org.w3c.dom.get
import kotlin.js.Promise

object ScriptLoader {

    enum class State {
        Loading,
        Loaded,
        Error,
    }

    data class Javascript(
        val src: String,
        val integrity: String?,
        val crossOrigin: String? = "anonymous",
        val referrerPolicy: String? = "no-referrer",
    )

    private val javascripts = mutableMapOf<Javascript, Deferred<HTMLScriptElement>>()

    fun load(script: Javascript): Deferred<HTMLScriptElement> {
        return javascripts.getOrPut(script) {
            mountScriptTag(script)
        }
    }

    private fun mountScriptTag(script: Javascript): Deferred<HTMLScriptElement> {

        return Promise { accept, reject ->

            val tag = (document.createElement("script") as HTMLScriptElement)

            tag.src = script.src
            tag.type = "text/javascript"
            script.crossOrigin?.let { tag.crossOrigin = it }
            script.referrerPolicy?.let { tag.asDynamic().referrerPolicy = it }
            tag.onerror = { e, _, _, _, _ ->
                reject(e)
            }
            tag.onload = { _ ->
                accept(tag)
            }

            document.getElementsByTagName("head")[0]?.appendChild(tag)

        }.asDeferred()
    }
}
