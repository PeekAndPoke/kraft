// <CodeBlock code>
package de.peekandpoke.kraft.examples.fomanticui.pages.howto.kraft.code

import de.peekandpoke.kraft.components.NoProps
import de.peekandpoke.kraft.components.PureComponent
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.html.Tag
import kotlinx.html.h4

@Suppress("FunctionName")
        /** This function is needed to instantiate a component */
fun Tag.PureExampleComponent() = comp {
    PureExampleComponent(it)
}

/** This is the implementation of the component */
class PureExampleComponent(ctx: NoProps) : PureComponent(ctx) {
    /** Each component needs to implement the render function */
    override fun VDom.render() {
        h4 { +"This is a pure component" }
    }
}
// </CodeBlock>
