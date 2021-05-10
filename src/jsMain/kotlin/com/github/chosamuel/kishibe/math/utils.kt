package com.github.chosamuel.kishibe.math

import com.github.chosamuel.kishibe.Drawer
import kotlin.math.PI
import kotlin.math.max
import kotlin.math.min

val Double.asRadians: Double
    get() = this / 360.0 * (PI*2.0)

val Double.asDegrees: Double
    get() = this * 180 / PI

fun mixAngle(leftAngle: Double, rightAngle: Double, x: Double): Double {
    val shortestAngle = ((((rightAngle - leftAngle) % 360) + 540) % 360) - 180;
    return (leftAngle + shortestAngle * x) % 360;
}

fun map(beforeLeft: Double, beforeRight: Double,
        afterLeft: Double, afterRight: Double,
        value: Double,
        clamp: Boolean = false): Double {

    val db = (beforeRight - beforeLeft)
    val da = (afterRight - afterLeft)

    return if (db != 0.0) {
        val n = (value - beforeLeft) / db
        afterLeft + (if (clamp) saturate(n) else n) * da
    } else {
        val n = value - beforeLeft
        afterLeft + (if (clamp) saturate(n) else n) * da
    }
}

fun saturate(x: Double) = max(0.0, min(1.0, x))

fun Drawer.randomPos():Vector2 {
    return Vec2(Random.nextDouble(width*1.0),Random.nextDouble(height*1.0))
}