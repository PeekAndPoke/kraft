package de.peekandpoke.kraft.components

import kotlinx.html.CommonAttributeGroupFacade
import kotlinx.html.js.*
import org.w3c.dom.events.*

/**
 * Adds an onAnimationEnd handler.
 *
 * The event is raised when a css animation ends.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/animationend_event
 */
fun CommonAttributeGroupFacade.onAnimationEnd(handler: (Event) -> Unit) {
    consumer.onTagEvent(this, "onanimationend", handler)
}

/**
 * Adds an onBlur handler.
 *
 * This event does NOT bubble while [onFocusOut] does.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/blur_event
 */
fun CommonAttributeGroupFacade.onBlur(handler: (Event) -> Unit) {
    @Suppress("UNCHECKED_CAST", "USELESS_CAST")
    onBlurFunction = handler as (Event) -> Unit
}

/**
 * Adds an onChange handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/change_event
 */
fun CommonAttributeGroupFacade.onChange(handler: (Event) -> Unit) {
    @Suppress("UNCHECKED_CAST", "USELESS_CAST")
    onChangeFunction = handler as (Event) -> Unit
}

/**
 * Adds an onClick handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/click_event
 */
fun CommonAttributeGroupFacade.onClick(handler: (MouseEvent) -> Unit) {
    @Suppress("UNCHECKED_CAST")
    onClickFunction = handler as (Event) -> Unit
}

/**
 * Adds an onClick handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/error_event
 */
fun CommonAttributeGroupFacade.onError(handler: (Event) -> Unit) {
    @Suppress("UNCHECKED_CAST")
    onErrorFunction = handler
}

/**
 * Adds an onFocus handler.
 *
 * This event does NOT bubble while [onFocusIn] does.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/focus_event
 */
fun CommonAttributeGroupFacade.onFocus(handler: (FocusEvent) -> Unit) {
    @Suppress("UNCHECKED_CAST")
    onFocusFunction = handler as (Event) -> Unit
}

/**
 * Add an onFocusIn handler.
 *
 * The event DOES bubble while [onFocus] does not.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/focusin_event
 */
fun CommonAttributeGroupFacade.onFocusIn(handler: (FocusEvent) -> Unit) {
    @Suppress("UNCHECKED_CAST")
    onFocusInFunction = handler as (Event) -> Unit
}

/**
 * Add an onFocusOut handler.
 *
 * This event DOES bubble while [onBlur] does not.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/focusout_event
 */
fun CommonAttributeGroupFacade.onFocusOut(handler: (FocusEvent) -> Unit) {
    @Suppress("UNCHECKED_CAST")
    onFocusOutFunction = handler as (Event) -> Unit
}

/**
 * Adds an onInput handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/input_event
 */
fun CommonAttributeGroupFacade.onInput(handler: (InputEvent) -> Unit) {
    @Suppress("UNCHECKED_CAST", "USELESS_CAST")
    onInputFunction = handler as (Event) -> Unit
}

/**
 * Adds an onKeyDown handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Document/keydown_event
 */
fun CommonAttributeGroupFacade.onKeyDown(handler: (KeyboardEvent) -> Unit) {
    @Suppress("UNCHECKED_CAST", "USELESS_CAST")
    onKeyDownFunction = handler as (Event) -> Unit
}

/**
 * Adds an onKeyPress handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Document/keypress_event
 */
@Deprecated("Use onKeyDown instead", ReplaceWith("onKeyDown"))
fun CommonAttributeGroupFacade.onKeyPress(handler: (KeyboardEvent) -> Unit) {
    @Suppress("UNCHECKED_CAST", "USELESS_CAST")
    onKeyPressFunction = handler as (Event) -> Unit
}

/**
 * Adds an onKeyUp handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Document/keyup_event
 */
fun CommonAttributeGroupFacade.onKeyUp(handler: (KeyboardEvent) -> Unit) {
    @Suppress("UNCHECKED_CAST", "USELESS_CAST")
    onKeyUpFunction = handler as (Event) -> Unit
}

/**
 * Adds an onMouseDown handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/mousedown_event
 */
fun CommonAttributeGroupFacade.onMouseDown(handler: (MouseEvent) -> Unit) {
    @Suppress("UNCHECKED_CAST", "USELESS_CAST")
    onMouseDownFunction = handler as (Event) -> Unit
}

/**
 * Adds an onMouseDown handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseenter_event
 */
fun CommonAttributeGroupFacade.onMouseEnter(handler: (MouseEvent) -> Unit) {
    @Suppress("UNCHECKED_CAST", "USELESS_CAST")
    consumer.onTagEvent(this, "onmouseenter", handler as (Event) -> Unit)
}

/**
 * Adds an onMouseDown handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseleave_event
 */
fun CommonAttributeGroupFacade.onMouseLeave(handler: (MouseEvent) -> Unit) {
    @Suppress("UNCHECKED_CAST", "USELESS_CAST")
    consumer.onTagEvent(this, "onmouseleave", handler as (Event) -> Unit)
}

/**
 * Adds an onMouseMove handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/mousemove_event
 */
fun CommonAttributeGroupFacade.onMouseMove(handler: (MouseEvent) -> Unit) {
    @Suppress("UNCHECKED_CAST", "USELESS_CAST")
    onMouseMoveFunction = handler as (Event) -> Unit
}

/**
 * Adds an onMouseOver handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseover_event
 */
fun CommonAttributeGroupFacade.onMouseOver(handler: (MouseEvent) -> Unit) {
    @Suppress("UNCHECKED_CAST", "USELESS_CAST")
    onMouseOverFunction = handler as (Event) -> Unit
}

/**
 * Adds an onMouseOut handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseout_event
 */
fun CommonAttributeGroupFacade.onMouseOut(handler: (MouseEvent) -> Unit) {
    @Suppress("UNCHECKED_CAST", "USELESS_CAST")
    onMouseOutFunction = handler as (Event) -> Unit
}

/**
 * Adds an onMouseUp handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseup_event
 */
fun CommonAttributeGroupFacade.onMouseUp(handler: (MouseEvent) -> Unit) {
    @Suppress("UNCHECKED_CAST", "USELESS_CAST")
    onMouseUpFunction = handler as (Event) -> Unit
}

/**
 * Add an onSelect handler.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/Element/select_event
 */
fun CommonAttributeGroupFacade.onSelect(handler: (InputEvent) -> Unit) {
    @Suppress("UNCHECKED_CAST", "USELESS_CAST")
    onSelectFunction = handler as (Event) -> Unit
}

/**
 * onSubmit handler
 */
fun CommonAttributeGroupFacade.onSubmit(handler: (Event) -> Unit) {
    @Suppress("UNCHECKED_CAST", "USELESS_CAST")
    onSubmitFunction = {
        it.preventDefault()
        handler(it)
    }
}
