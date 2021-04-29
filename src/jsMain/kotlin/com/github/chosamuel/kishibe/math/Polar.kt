package com.github.chosamuel.kishibe.math


import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

data class Polar(val theta: Double, val radius: Double = 1.0){

    companion object {
        /** Constructs equivalent polar coordinates from the Cartesian coordinate system. */
        fun fromVector(vector: Vec2): Polar {
            val r = vector.length
            return Polar(
                if (r == 0.0) 0.0 else atan2(vector.y, vector.x).asDegrees,
                r
            )
        }
    }

    /** Constructs equivalent Cartesian coordinates from the polar representation. */
    val cartesian: Vec2
        get() {
            val theta = theta.asRadians
            val x = cos(theta)
            val y = sin(theta)
            return Vec2(x, y) * radius
        }

    operator fun plus(s: Polar) = Polar(theta + s.theta, radius + s.radius)
    operator fun minus(s: Polar) = Polar(theta - s.theta, radius - s.radius)
    operator fun times(s: Polar) = Polar(theta * s.theta, radius * s.radius)

    operator fun times(s: Double) = Polar(theta * s, radius * s)
    operator fun div(s: Double) = Polar(theta / s, radius / s)
}