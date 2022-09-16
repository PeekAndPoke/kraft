package de.peekandpoke.kraft.utils

import de.peekandpoke.kraft.streams.StreamSource
import kotlinx.browser.document
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.asDeferred
import org.w3c.dom.HTMLScriptElement
import org.w3c.dom.get
import kotlin.js.Promise

object ScriptLoader {

    data class Javascript(
        val src: String,
        val integrity: String? = null,
        val crossOrigin: String? = "anonymous",
        val referrerPolicy: String? = "no-referrer",
    )

    class Error(message: String, val details: Any? = null) : Throwable(message = message)

    private val jsMap = mutableMapOf<Javascript, Pair<HTMLScriptElement, Deferred<HTMLScriptElement>>>()

    private val jsSource = StreamSource<Set<Javascript>>(emptySet())

    val javascripts = jsSource.readonly

    fun hasLoaded(script: Javascript): Boolean {
        return jsMap.containsKey(script)
    }

    fun loadJavascriptAsync(src: String): Deferred<HTMLScriptElement> {
        return loadAsync(
            Javascript(src = src)
        )
    }

    fun loadAsync(script: Javascript): Deferred<HTMLScriptElement> {
        val (_, deferred) = jsMap.getOrPut(script) {
            mountAsync(script)
        }

        jsSource(jsMap.keys)

        return deferred
    }

    fun unloadJavascript(src: String) {
        unload(
            Javascript(src = src)
        )
    }

    fun unload(javascript: Javascript) {

        jsMap.remove(javascript)?.let { (tag, _) ->
            tag.remove()
        }

        jsSource(jsMap.keys)
    }

    private fun mountAsync(script: Javascript): Pair<HTMLScriptElement, Deferred<HTMLScriptElement>> {

        val tag = (document.createElement("script") as HTMLScriptElement)

        tag.src = script.src
        tag.type = "text/javascript"
        script.crossOrigin?.let { tag.crossOrigin = it }
        script.referrerPolicy?.let { tag.asDynamic().referrerPolicy = it }

        val promise = Promise { accept, reject ->

            tag.onload = { _ ->
                accept(tag)
            }

            tag.onerror = { event, url, line, col, errorObj ->
                val details = mapOf(
                    "event" to event,
                    "url" to url,
                    "line" to line,
                    "col" to col,
                    "errorObj" to errorObj,
                )

                unload(script)

                reject(Error("Error loading script ${script.src}", details.js))
            }

            document.getElementsByTagName("head")[0]?.appendChild(tag)
        }.asDeferred()

        return tag to promise
    }
}
