package de.peekandpoke.kraft.testbed

import de.peekandpoke.kraft.testbed.KQuery.Selector.Companion.cssSelector
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import org.w3c.dom.asList

class KQuery<E : Element>(val elements: List<E>) {

    interface Selector {

        companion object {
            val String.cssSelector get() = CSS(selector = this)
        }

        suspend fun <E: Element> applyTo(input: KQuery<E>): KQuery<Element>

        data class CSS(val selector: String): Selector {

            override suspend fun <E : Element> applyTo(input: KQuery<E>): KQuery<Element> {

                val found = input.elements.flatMap {
                    it.querySelectorAll(selector)
                        .asList()
                        .filterIsInstance<Element>()
                }

                return KQuery(elements = found.distinct())
            }
        }
    }

    val size: Int get() = elements.size

    fun get(idx: Int): E {
        return elements[idx]
    }

    fun first(): E {
        return get(0)
    }

    fun getOrNull(idx: Int): E? {
        return elements.getOrNull(idx)
    }

    suspend fun find(selector: Selector): KQuery<Element> {
        return selector.applyTo(this)
    }

    suspend fun selectCss(cssSelector: String): KQuery<Element> {
        return find(cssSelector.cssSelector)
    }
}

fun <E : Element> KQuery<E>.click(): KQuery<E> = apply {
    elements
        .filterIsInstance<HTMLElement>()
        .forEach { it.click() }
}

fun <E : Element> KQuery<E>.textContent(separator: String = ""): String {
    return elements
        .mapNotNull { it.textContent }
        .joinToString(separator) { it }
}

fun <E : Element> KQuery<E>.containsText(text: String): Boolean {
    return elements.any {
        it.textContent?.contains(text) ?: false
    }
}
