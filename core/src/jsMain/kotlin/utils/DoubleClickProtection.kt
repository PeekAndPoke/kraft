package de.peekandpoke.kraft.utils

import de.peekandpoke.kraft.components.Component

class DoubleClickProtection(component: Component<*>) {

    private var counter: Int by component.value(0)

    val canRun: Boolean get() = counter == 0

    val cannotRun: Boolean get() = !canRun

    operator fun invoke(block: suspend () -> Unit) = runAsync(block)

    fun runAsync(block: suspend () -> Unit) {
        if (cannotRun) {
            return
        }

        counter++

        launch {
            try {
                block()
            } catch (e: Throwable) {
                throw e
            } finally {
                counter--
            }
        }
    }

    suspend fun <R> runBlocking(block: suspend () -> R): R? {
        if (cannotRun) {
            return null
        }

        counter++

        return try {
            block()
        } catch (e: Throwable) {
            throw e
        } finally {
            counter--
        }
    }
}
