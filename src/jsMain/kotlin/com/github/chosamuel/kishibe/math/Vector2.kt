package com.github.chosamuel.kishibe.math

//BASED ON https://github.com/openrndr/openrndr/blob/master/openrndr-math/src/main/kotlin/org/openrndr/math/Vector2.kt

import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin
import kotlin.math.sqrt

class Vector2(var x: Double = 0.0, var y: Double = 0.0) {

    constructor(x: Double) : this(x, x)

    val length: Double
        get() = sqrt(x * x + y * y)

    val squaredLength: Double
        get() = x * x + y * y

    val floored: Vector2
        get() = Vector2(floor(x),floor(y))

    val normalized: Vector2
        get() {
            val localLength = length
            return if (localLength > 0.0) {
                this / length
            } else {
                Vector2(0.0,0.0)
            }
        }

    infix fun cross(right: Vector2) = x * right.y - y * right.x

    infix fun dot(right: Vector2) = x * right.x + y * right.y

    infix fun reflect(surfaceNormal: Vector2): Vector2 = this - surfaceNormal * (this dot surfaceNormal) * 2.0

    fun rotate(degrees: Double, origin: Vector2 = Vector2(0.0,0.0)): Vector2 {
        val p = this - origin
        val a = degrees.asRadians

        val w = Vector2(
            p.x * cos(a) - p.y * sin(a),
            p.y * cos(a) + p.x * sin(a)
        )

        return w + origin
    }
    operator fun plus(vector2: Vector2) = Vector2(x + vector2.x, y + vector2.y)
    operator fun plus(d: Double) = Vector2(x + d, y + d)

    operator fun minus(vector2: Vector2) = Vector2(x - vector2.x, y - vector2.y)
    operator fun minus(d: Double) = Vector2(x - d, y - d)

    operator fun times(d: Double) = Vector2(x * d, y * d)
    operator fun times(v: Vector2) = Vector2(x * v.x, y * v.y)

    operator fun div(d: Double) = Vector2(x / d, y / d)
    operator fun div(d: Vector2) = Vector2(x / d.x, y / d.y)

    /** Calculates the Euclidean distance to [other]. */
    fun distanceTo(other: Vector2): Double {
        val dx = other.x - x
        val dy = other.y - y
        return sqrt(dx * dx + dy * dy)
    }

    /** Calculates the squared Euclidean distance to [other]. */
    fun squaredDistanceTo(other: Vector2): Double {
        val dx = other.x - x
        val dy = other.y - y
        return dx * dx + dy * dy
    }
    fun fromPolar(polar: Polar): Vector2 {
        val theta = polar.theta.asRadians
        val x = cos(theta)
        val y = sin(theta)
        return Vector2(x, y) * polar.radius
    }
    fun mix(o: Vector2, mix: Double): Vector2 = this * (1 - mix) + o * mix
}