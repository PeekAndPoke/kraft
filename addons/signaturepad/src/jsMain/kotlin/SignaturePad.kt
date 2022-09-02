package de.peekandpoke.kraft.addons.signaturepad

import de.peekandpoke.kraft.addons.signaturepad.js.signature_pad
import de.peekandpoke.kraft.addons.signaturepad.js.trim_canvas
import de.peekandpoke.kraft.addons.styling.css
import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.components.Ctx
import de.peekandpoke.kraft.components.comp
import de.peekandpoke.kraft.vdom.VDom
import kotlinx.browser.window
import kotlinx.css.height
import kotlinx.css.pct
import kotlinx.css.width
import kotlinx.html.Tag
import kotlinx.html.canvas
import kotlinx.html.div
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.Event
import kotlin.math.max

@Suppress("FunctionName")
fun Tag.SignaturePad(
    onChange: (SignaturePad) -> Unit = {},
) = comp(
    SignaturePad.Props(
        onChange = onChange,
    )
) {
    SignaturePad(it)
}

class SignaturePad(ctx: Ctx<Props>) : Component<SignaturePad.Props>(ctx) {

    //  STATE  //////////////////////////////////////////////////////////////////////////////////////////////////

    data class Props(
        val onChange: (SignaturePad) -> Unit
    )

    private var pad: signature_pad.SignaturePad? = null

    //  IMPL  ///////////////////////////////////////////////////////////////////////////////////////////////////

    fun getPng(): String? = getCanvas()?.toDataURL("image/png")

    fun getPngTrimmed(): String? = getCanvasTrimmed()?.toDataURL("image/png")

    fun getSvg(): String? = getCanvas()?.toDataURL("image/svg+xml")

    fun getSvgTrimmed(): String? = getCanvasTrimmed()?.toDataURL("image/svg+xml")

    fun getJpg(quality: Double = 0.9): String? = getCanvas()?.toDataURL("image/jpeg", quality)

    fun getJpgTrimmed(quality: Double = 0.9): String? = getCanvasTrimmed()?.toDataURL("image/jpeg", quality)

    // Life-Cycle /////////////////////////////////////////////////////////////////////////////////////////

    override fun onMount() {

        dom?.let {
            getCanvas()?.let { canvas ->
                pad = signature_pad.SignaturePad(canvas)

                pad?.addEventListener("endStroke", ::onPadEndStroke)

                resize()
            }
        }

        window.addEventListener("resize", ::onWindowResize)
    }

    override fun onUnmount() {
        window.removeEventListener("resize", ::onWindowResize)

        pad?.let {
            it.removeEventListener("endStroke", ::onPadEndStroke)
            it.off()
        }
    }

    // Rendering /////////////////////////////////////////////////////////////////////////////////////////

    override fun VDom.render() {
        div {
            css {
                width = 100.pct
                height = 100.pct
            }

            canvas { }
        }
    }

    // Helpers /////////////////////////////////////////////////////////////////////////////////////////

    private fun getCanvas(): HTMLCanvasElement? {
        return dom?.querySelector("canvas") as? HTMLCanvasElement
    }

    private fun getCanvasTrimmed(): HTMLCanvasElement? {

        return getCanvas()?.let { original ->

            val cloned = original.cloneNode() as HTMLCanvasElement

            (cloned.getContext("2d") as CanvasRenderingContext2D).drawImage(original, 0.0, 0.0)

            trim_canvas.trimCanvas(cloned)
        }
    }

    private fun resize() {

        dom?.let { dom ->

            val ratio = max(window.asDynamic()["devicePixelRatio"] as? Double ?: 1.0, 1.0)

            getCanvas()?.let { canvas ->

                canvas.width = dom.offsetWidth
                canvas.height = dom.offsetHeight

                canvas.width = (canvas.offsetWidth * ratio).toInt()
                canvas.height = (canvas.offsetHeight * ratio).toInt()
                (canvas.getContext("2d") as? CanvasRenderingContext2D)?.scale(ratio, ratio)

                // otherwise isEmpty() might return incorrect value
                pad?.clear()
            }
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onWindowResize(evt: Event) {
        resize()
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onPadEndStroke(evt: Event) {
        props.onChange(this)
    }
}
