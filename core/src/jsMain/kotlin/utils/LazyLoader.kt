package de.peekandpoke.kraft.utils

import de.peekandpoke.kraft.store.Stream
import de.peekandpoke.kraft.store.StreamSource
import kotlinx.coroutines.flow.Flow

class LazyLoader {

    private val keys: MutableMap<Key<*>, Stream<*>> = mutableMapOf()

    class Key<T>(val name: String) {
        override fun equals(other: Any?): Boolean {
            return other != null &&
                    other is Key<*> &&
                    name == other.name
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }
    }

    fun <T> add(key: Key<T>, default: T, flow: suspend () -> Flow<T>): Stream<T> {
        @Suppress("UNCHECKED_CAST")
        return keys.getOrPut(key) {
            StreamSource(default).also { stream ->
                launch {
                    flow().collect { stream(it) }
                }
            }
        } as Stream<T>
    }
}

