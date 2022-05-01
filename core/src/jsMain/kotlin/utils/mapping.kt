package de.peekandpoke.kraft.utils

import de.peekandpoke.ultra.common.datetime.MpLocalDate

fun <T> identity(it: T): T = it

fun stringToString(it: String?): String = it ?: ""

fun numberToString(it: Number?): String = it?.toString() ?: ""

fun stringToInt(it: String): Int = if (it.isBlank()) 0 else it.toInt()

fun stringToFloat(it: String): Float = if (it.isBlank()) 0.0f else it.toFloat()

fun stringToDouble(it: String): Double = if (it.isBlank()) 0.0 else it.toDouble()

fun stringToNumber(it: String): Number = if (it.isBlank()) 0.0 else it.toDouble()

fun dateToString(it: MpLocalDate): String = it.format("yyyy-MM-dd")

fun stringToDate(it: String): MpLocalDate = MpLocalDate.parse(it)
