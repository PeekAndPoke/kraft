package de.peekandpoke.kraft.streams

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class StreamSourceSpec : StringSpec({

    "Number of subscriptions must be correct" {

        val source = StreamSource(10) as StreamSourceImpl<Int>

        source.subscriptions.size shouldBe 0

        val first = source.subscribeToStream {}

        val second = source.subscribeToStream {}

        source.subscriptions.size shouldBe 2

        first()
        first()

        source.subscriptions.size shouldBe 1

        second()

        source.subscriptions.size shouldBe 0
    }

    "Subscriptions must be called" {

        val source = StreamSource(10) as StreamSourceImpl<Int>

        var first = 0
        var second = 0

        source.subscribeToStream {
            first = it * 10
        }

        source.subscribeToStream {
            second = it * 100
        }

        source(10)

        first shouldBe 100
        second shouldBe 1000

        source.removeAllSubscriptions()
    }
})
