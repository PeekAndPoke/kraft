package de.peekandpoke.kraft.addons.forms

import kotlinx.js.Symbol

object FormStorage {

    /**
     * Storage key
     */
    class Key<T>(
        /** Ref to the [FormStorage] */
        private val storage: FormStorage,
        /** Unique name of the key */
        val name: String,
    ) {
        private val symbol = Symbol(name)

        /**
         * Gets the value stored for this key
         */
        fun get(): T? {
            return storage.get(symbol)
        }

        /**
         * Sets the [value] stored for this key
         */
        fun set(value: T?) {
            storage.set(symbol, value)
        }

        /**
         * Remove this key from the storage.
         */
        fun remove() {
            storage.remove(symbol)
        }
    }

    /**
     * Counter for the next symbol key
     */
    private var nextKeyCounter: Int = 1

    /**
     * The internal storage
     */
    private var storage: MutableMap<Symbol, Any?> = mutableMapOf()

    /**
     * Create the next key.
     */
    fun <T> getNextKey(): Key<T> = Key(
        storage = this,
        name = "--form-key-$nextKeyCounter--".also { nextKeyCounter += 1 },
    )

    /**
     * Gets the internal storage
     */
    fun getStorage(): Map<Symbol, Any?> = storage.toMap()

    /**
     * Gets the value for the given [symbol].
     */
    private fun <T> get(symbol: Symbol): T? {
        @Suppress("UNCHECKED_CAST", "ReplaceGetOrSet")
        return storage.get(symbol) as? T?
    }

    /**
     * Gets the [value] for the given [symbol].
     */
    private fun <T> set(symbol: Symbol, value: T) {
        @Suppress("ReplaceGetOrSet")
        storage.set(symbol, value)
    }

    /**
     * Gets the given [symbol] from the internal [storage].
     */
    private fun remove(symbol: Symbol) {
        storage.remove(symbol)
    }
}
