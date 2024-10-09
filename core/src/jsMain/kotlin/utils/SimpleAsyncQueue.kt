package de.peekandpoke.kraft.utils

import de.peekandpoke.ultra.common.shift

class SimpleAsyncQueue {
    private val jobs = mutableListOf<suspend () -> Unit>()

    private var running: Boolean = false

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

            jobs.shift()?.let { job ->
                launch {
                    try {
                        job()
                    } catch (e: Throwable) {
                        console.error("Job failed", e)
                    }
                }

                running = false

                runNext()
            }
        }
    }
}
