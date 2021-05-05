package com.github.chosamuel.kishibe.math

//BASED ON https://github.com/openrndr/openrndr/blob/master/openrndr-math/src/main/kotlin/org/openrndr/math/Vector2.kt

import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin
import kotlin.math.sqrt

class Vec2(var x: Double = 0.0, var y: Double = 0.0) {

    constructor(x: Double) : this(x, x)

    val length: Double
        get() = sqrt(x * x + y * y)

    val squaredLength: Double
        get() = x * x + y * y

    val floored: Vec2
        get() = Vec2(floor(x),floor(y))

    val normalized: Vec2
        get() {
            val localLength = length
            return if (localLength > 0.0) {
                this / length
            } else {
                Vec2(0.0,0.0)
            }
        }

    infix fun cross(right: Vec2) = x * right.y - y * right.x

    infix fun dot(right: Vec2) = x * right.x + y * right.y

    infix fun reflect(surfaceNormal: Vec2): Vec2 = this - surfaceNormal * (this dot surfaceNormal) * 2.0

    fun rotate(degrees: Double, origin: Vec2 = Vec2(0.0,0.0)): Vec2 {
        val p = this - origin
        val a = degrees.asRadians

        val w = Vec2(
            p.x * cos(a) - p.y * sin(a),
            p.y * cos(a) + p.x * sin(a)
        )

        return w + origin
    }
    operator fun plus(vector2: Vec2) = Vec2(x + vector2.x, y + vector2.y)
    operator fun plus(d: Double) = Vec2(x + d, y + d)

    operator fun minus(vector2: Vec2) = Vec2(x - vector2.x, y - vector2.y)
    operator fun minus(d: Double) = Vec2(x - d, y - d)

    operator fun times(d: Double) = Vec2(x * d, y * d)
    operator fun times(v: Vec2) = Vec2(x * v.x, y * v.y)

    operator fun div(d: Double) = Vec2(x / d, y / d)
    operator fun div(d: Vec2) = Vec2(x / d.x, y / d.y)

    /** Calculates the Euclidean distance to [other]. */
    fun distanceTo(other: Vec2): Double {
        val dx = other.x - x
        val dy = other.y - y
        return sqrt(dx * dx + dy * dy)
    }

    /** Calculates the squared Euclidean distance to [other]. */
    fun squaredDistanceTo(other: Vec2): Double {
        val dx = other.x - x
        val dy = other.y - y
        return dx * dx + dy * dy
    }
    fun fromPolar(polar: Polar): Vec2 {
        val theta = polar.theta.asRadians
        val x = cos(theta)
        val y = sin(theta)
        return Vec2(x, y) * polar.radius
    }
    fun mix(o: Vec2, mix: Double): Vec2 = this * (1 - mix) + o * mix
}