@file:Suppress("FunctionName", "NOTHING_TO_INLINE")

package de.peekandpoke.kraft.addons.prismjs

import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.components.Ctx
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.utils.launch
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.browser.document
import kotlinx.dom.clear
import kotlinx.html.Tag
import kotlinx.html.code
import kotlinx.html.div
import kotlinx.html.pre
import org.intellij.lang.annotations.Language
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLPreElement
import org.w3c.dom.set

fun Tag.Prism(language: String, code: String, plugins: List<Prism.Plugin>) =
    comp(Prism.Props(language = language, code = code, plugins = plugins)) { Prism(it) }

inline fun Tag.PrismAtom(@Language("atom") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "atom", code = code, plugins = plugins.toList())

inline fun Tag.PrismCLike(@Language("c") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "clike", code = code, plugins = plugins.toList())

inline fun Tag.PrismCss(@Language("css") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "css", code = code, plugins = plugins.toList())

inline fun Tag.PrismDart(@Language("dart") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "dart", code = code, plugins = plugins.toList())

inline fun Tag.PrismHtml(@Language("html") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "html", code = code, plugins = plugins.toList())

inline fun Tag.PrismKotlin(@Language("kotlin") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "kotlin", code = code, plugins = plugins.toList())

inline fun Tag.PrismKotlinScript(@Language("kts") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "kts", code = code, plugins = plugins.toList())

inline fun Tag.PrismJava(@Language("java") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "java", code = code, plugins = plugins.toList())

inline fun Tag.PrismJavascript(@Language("javascript") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "javascript", code = code, plugins = plugins.toList())

inline fun Tag.PrismJson(@Language("json") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "json", code = code, plugins = plugins.toList())

inline fun Tag.PrismJson5(@Language("json5") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "json5", code = code, plugins = plugins.toList())

inline fun Tag.PrismJsonp(@Language("jsonp") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "jsonp", code = code, plugins = plugins.toList())

inline fun Tag.PrismLess(@Language("less") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "less", code = code, plugins = plugins.toList())

inline fun Tag.PrismMarkup(@Language("markup") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "markup", code = code, plugins = plugins.toList())

inline fun Tag.PrismPhp(@Language("php") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "php", code = code, plugins = plugins.toList())

inline fun Tag.PrismPlain(code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "plain", code = code, plugins = plugins.toList())

inline fun Tag.PrismRegex(@Language("regex") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "regex", code = code, plugins = plugins.toList())

inline fun Tag.PrismRuby(@Language("ruby") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "ruby", code = code, plugins = plugins.toList())

inline fun Tag.PrismRust(@Language("rust") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "rust", code = code, plugins = plugins.toList())

inline fun Tag.PrismRss(@Language("rss") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "rss", code = code, plugins = plugins.toList())

inline fun Tag.PrismSass(@Language("sass") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "sass", code = code, plugins = plugins.toList())

inline fun Tag.PrismScss(@Language("scss") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "scss", code = code, plugins = plugins.toList())

inline fun Tag.PrismSsml(@Language("ssml") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "ssml", code = code, plugins = plugins.toList())

inline fun Tag.PrismSvg(@Language("svg") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "svg", code = code, plugins = plugins.toList())

inline fun Tag.PrismText(code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "text", code = code, plugins = plugins.toList())

inline fun Tag.PrismTypescript(@Language("typescript") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "typescript", code = code, plugins = plugins.toList())

inline fun Tag.PrismXml(@Language("xml") code: String, vararg plugins: Prism.Plugin) =
    Prism(language = "xml", code = code, plugins = plugins.toList())

class Prism(ctx: Ctx<Props>) : Component<Prism.Props>(ctx) {

    //  PROPS  //////////////////////////////////////////////////////////////////////////////////////////////////

    data class Props(
        val language: String,
        val code: String,
        val plugins: List<Plugin>,
    )

    sealed class Plugin {

        abstract suspend fun load()
        abstract fun applyTo(pre: HTMLPreElement)

        object InlineColor : Plugin() {
            override suspend fun load() {
                prismJsInternals.plugins.loadInlineColor()
            }

            override fun applyTo(pre: HTMLPreElement) {
                pre.className += " inline-color"
            }
        }

        data class LineNumbers(
            val softWrap: Boolean = false,
            val start: Int = 1,
        ) : Plugin() {
            override suspend fun load() {
                prismJsInternals.plugins.loadLineNumbers()
            }

            override fun applyTo(pre: HTMLPreElement) {
                pre.className += " line-numbers"
                pre.dataset["start"] = start.toString()
                if (softWrap) {
                    pre.style.whiteSpace = "pre-wrap"
                }
            }
        }

        data class ShowLanguage(
            val language: String? = null,
        ) : Plugin() {
            override suspend fun load() {
                prismJsInternals.plugins.loadShowLanguage()
            }

            override fun applyTo(pre: HTMLPreElement) {
                pre.className += " show-language"
                language?.let {
                    pre.dataset["language"] = it
                }
            }
        }
    }

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    init {
        launch {
            prismJsInternals.styles.loadDefaultTheme()

            props.plugins.forEach { it.load() }

            prismJsInternals.languages.load(props.language)

            createContent()
        }
    }

    private fun createContent() {

        dom?.let { d ->
            val pre = (document.createElement("pre") as HTMLPreElement).also { pre ->

                pre.className = "language-${props.language}"

                props.plugins.forEach { it.applyTo(pre) }

                (document.createElement("code") as HTMLElement).also { code ->

                    val text = document.createTextNode(props.code)

                    code.append(text)
                    pre.append(code)
                }
            }

            d.clear()
            d.append(pre)

            PrismJsDefinition.highlightAllUnder(d)
        }
    }

    override fun VDom.render() {
        // Initially we create a placeholder which is filled by [createContent]
        div("prism") {
            pre("language-${props.language}") {
                code {
                    +props.code
                }
            }
        }
    }
}
