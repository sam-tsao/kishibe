package com.github.chosamuel.kishibe.application
import kotlin.js.Date

data class Timer(
    val startTime: Double = Date().getTime()
) {
    fun getTimeElapsed(): Double = Date().getTime() - startTime
}