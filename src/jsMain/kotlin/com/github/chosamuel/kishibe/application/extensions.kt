package com.github.chosamuel.kishibe.application

import com.github.chosamuel.kishibe.math.map

fun Application.getTimeElapsed(): Double = timer.getTimeElapsed()
fun Application.frameRate(): Double = frameCount/ getTimeElapsed() * 1000
fun Application.getWidth(): Double = canvas.clientWidth * 1.0
fun Application.getHeight(): Double = canvas.clientHeight * 1.0

fun Application.worldToClip(x: Double, y: Double): Array<Float>{
    return arrayOf(
        map(
            0.0,
            canvas.clientWidth * 1.0,
            -1.0,
            1.0,
            x
        ).toFloat(),
        map(
            0.0,
            canvas.clientHeight * 1.0,
            1.0,
            -1.0,
            y
        ).toFloat(),
    )
}