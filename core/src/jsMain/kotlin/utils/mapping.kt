package de.peekandpoke.kraft.utils

// TODO: move to ultra-common-mp
fun <T> identity(it: T): T = it

// TODO: move to ultra-common-mp
fun numberToString(it: Number): String = it.toString()

// TODO: move to ultra-common-mp
fun stringToInt(it: String): Int = if (it.isBlank()) 0 else it.toInt()

// TODO: move to ultra-common-mp
fun stringToFloat(it: String): Float = if (it.isBlank()) 0.0f else it.toFloat()

// TODO: move to ultra-common-mp
fun stringToDouble(it: String): Double = if (it.isBlank()) 0.0 else it.toDouble()
