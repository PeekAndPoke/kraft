package de.peekandpoke.kraft.addons.forms

interface FormField<T> {

    /**
     * The errors the field currently has
     */
    val errors: List<String>

    /**
     * Returns true when the field has errors
     */
    val hasErrors get() = errors.isNotEmpty()

    /**
     * Marker if the component was touched.
     */
    val touched: Boolean

    /**
     * Marks the form field as touched.
     */
    fun touch()

    /**
     * Marks the form field as not touched.
     */
    fun untouch()

    /**
     * Validates all rules and returns true if all rules are fulfilled.
     */
    fun validate(): Boolean
}
