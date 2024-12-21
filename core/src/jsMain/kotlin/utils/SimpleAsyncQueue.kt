package de.peekandpoke.kraft.utils

import de.peekandpoke.ultra.common.shift

class SimpleAsyncQueue(
    val onTaskFinished: () -> Unit = {}
) {
    private val jobs = mutableListOf<suspend () -> Unit>()

    private var running: Boolean = false

    fun isEmpty() = jobs.isEmpty()
    fun size() = jobs.size

    fun add(jobs: suspend () -> Unit) {
        this.jobs.add(jobs)
        runNext()
    }

    private fun runNext() {

        if (jobs.isEmpty()) {
            return
        }

        if (!running) {
            running = true

            jobs.firstOrNull()?.let { job ->
                launch {
                    try {
                        job()
                    } catch (e: Throwable) {
                        console.error("Job failed", e)
                    }
                    // Remove the job
                    jobs.shift()
                    // Notify
                    onTaskFinished()
                }

                running = false

                runNext()
            }
        }
    }
}
