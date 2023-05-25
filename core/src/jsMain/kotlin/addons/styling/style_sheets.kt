package de.peekandpoke.kraft.addons.styling

import kotlinx.browser.document
import kotlinx.css.CssBuilder
import org.w3c.dom.HTMLHeadElement
import org.w3c.dom.get
import kotlin.random.Random

object StyleSheets {

    private val mounted = mutableSetOf<String>()

    private val mangleMap = mutableMapOf<String, MutableSet<String>>()

    private val random = Random(1)

    private val head: HTMLHeadElement? by lazy(LazyThreadSafetyMode.NONE) {
        document.getElementsByTagName("head")[0] as? HTMLHeadElement
    }

    fun mount(style: StyleSheetDefinition) {

        val css = style.css

        if (!mounted.contains(css)) {
            mounted.add(css)

            val styleNode = document.createElement("style")
            styleNode.setAttribute("id", "injected-${css.hashCode()}")
            styleNode.textContent = css

//            console.log("body", body)
//            console.log("style", styleNode)

            head?.appendChild(styleNode)
        }
    }

    internal fun mangleClassName(cls: String): String {
        val set = mangleMap.getOrPut(cls) { mutableSetOf() }

        val chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ0123456789"
        val numChars = chars.length

        lateinit var mangled: String

        do {
            mangled = cls + "_" + (0..6).joinToString("") {
                chars[random.nextInt(until = numChars)].toString()
            }
        } while (mangled in set)

        set.add(mangled)

        return mangled
    }
}

interface StyleSheetDefinition {
    val css: String
}

data class RawStyleSheet(override val css: String) : StyleSheetDefinition

abstract class StyleSheet : StyleSheetDefinition {

    override val css: String
        get() = builder.toString()

    private val builder = CssBuilder()

    private var counter = 1

    fun rule(block: CssBuilder.() -> Unit): String {
        return makeRule(null, block)
    }

    fun rule(contextSelector: String, block: CssBuilder.() -> Unit): String {
        return makeRule(contextSelector, block)
    }

    private fun makeRule(contextSelector: String? = null, block: CssBuilder.() -> Unit): String {
        val cssClassName = "r$counter"

        val mangled = StyleSheets.mangleClassName(cssClassName)

        builder.apply {
            if (contextSelector == null) {
                rule(".$mangled") { block() }
            } else {
                rule("$contextSelector .$mangled") {
                    block()
                }
            }
        }

        return mangled
    }
}
