package de.peekandpoke.kraft.utils

data class Vector2D(val x: Double, val y: Double) {
    companion object {
        val zero = Vector2D(0.0, 0.0)
    }

    operator fun plus(other: Vector2D) = Vector2D(x = x + other.x, y = y + other.y)

    operator fun minus(other: Vector2D) = Vector2D(x = x - other.x, y = y - other.y)
}

