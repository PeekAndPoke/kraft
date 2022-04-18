package de.peekandpoke.kraft

import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.kraft.vdom.preact.PreactVDomEngine
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldContain
import kotlinx.browser.document
import kotlinx.coroutines.delay
import kotlinx.dom.appendElement
import kotlinx.html.h1
import org.w3c.dom.HTMLBodyElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.get

class PreactFundamentalsSpec : StringSpec({

    suspend fun usePreactTestBed(
        render: VDom.() -> Unit,
        test: suspend (HTMLDivElement) -> Unit
    ) {
        val body = document.getElementsByTagName("body")[0] as HTMLBodyElement

        val testBed = body.appendElement("div") {
            id = "kraft-testbed"
        } as HTMLDivElement

        PreactVDomEngine(testBed) {
            render()
        }

        delay(10)

        test(testBed)

        delay(10)

        testBed.remove()
    }

    "Render 'Hello world'" {

        usePreactTestBed(
            { h1 { +"Hello World!" } }
        ) {
            it.textContent shouldContain "Hello World"
        }
    }

    "Render 'Hello Mars'" {

        usePreactTestBed(
            { h1 { +"Hello Mars!" } }
        ) {
            it.textContent shouldContain "Hello Mars!"
        }
    }
})
