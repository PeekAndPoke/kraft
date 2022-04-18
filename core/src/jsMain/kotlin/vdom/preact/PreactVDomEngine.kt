package de.peekandpoke.kraft.vdom.preact

import de.peekandpoke.kraft.utils.jsObject
import de.peekandpoke.kraft.vdom.VDom
import de.peekandpoke.kraft.vdom.VDomEngine
import de.peekandpoke.kraft.vdom.VDomTagConsumer
import org.w3c.dom.HTMLElement
import de.peekandpoke.kraft.components.Component as KraftComponent
import preact.Component as PreactComponent

class PreactVDomEngine : VDomEngine {

    companion object {
        operator fun invoke(element: HTMLElement, view: VDom.() -> Any?): PreactVDomEngine =
            PreactVDomEngine().apply {
                mount(element, view)
            }
    }

    override fun mount(element: HTMLElement, view: VDom.() -> Any?) {

        val lowLevelRoot = render { view() }

        preact.render(lowLevelRoot, element)
    }

    override fun createTagConsumer(host: KraftComponent<*>?): VDomTagConsumer = PreactTagConsumer(this, host)

    override fun triggerRedraw(component: KraftComponent<*>) {
        (component.lowLevelBridgeComponent as? PreactComponent)?.setState(jsObject)
    }
}
