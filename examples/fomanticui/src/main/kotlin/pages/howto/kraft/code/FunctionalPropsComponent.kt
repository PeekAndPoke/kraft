// <CodeBlock code>
package de.peekandpoke.kraft.examples.fomanticui.pages.howto.kraft.code

import de.peekandpoke.kraft.components.component
import kotlinx.html.h4

val FunctionalPropsComponent = component { first: Int, second: Int ->
    h4 { +"$first + $second = ${first + second}" }
}
// </CodeBlock>
