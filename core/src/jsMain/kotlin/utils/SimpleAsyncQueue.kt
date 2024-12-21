package de.peekandpoke.kraft.utils

import de.peekandpoke.ultra.common.shift

class SimpleAsyncQueue(
    val onTaskFinished: () -> Unit = {}
) {
    private val jobs = mutableListOf<suspend () -> Unit>()

    private var running: Boolean = false

    fun isEmpty() = jobs.isEmpty()
    fun size() = jobs.size

    fun add(job: suspend () -> Unit) {
        jobs.add(job)
        runNext()
    }

    private fun runNext() {

        if (jobs.isEmpty()) {
            return
        }

        if (!running) {
            running = true

            launch {
                jobs.firstOrNull()?.let { job ->
                    try {
                        job()
                    } catch (e: Throwable) {
                        console.error("Job failed", e)
                    }
                }

                // Remove the job
                jobs.shift()
                // Notify
                try {
                    onTaskFinished()
                } finally {
                }

                running = false

                runNext()
            }
        }
    }
}
