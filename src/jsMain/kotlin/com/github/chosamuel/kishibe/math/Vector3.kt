package com.github.chosamuel.kishibe.math

import kotlin.math.*

/*
Copyright (c) 2018, Edwin Jakobs, RNDR
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies,
either expressed or implied, of the FreeBSD Project.
*/

/** Double-precision 3D vector. */
data class Vector3(val x: Double, val y: Double, val z: Double){
    constructor(x: Double) : this(x, x, x)

    companion object {
        val ZERO = Vector3(0.0, 0.0, 0.0)
        val ONE = Vector3(1.0, 1.0, 1.0)
        val UNIT_X = Vector3(1.0, 0.0, 0.0)
        val UNIT_Y = Vector3(0.0, 1.0, 0.0)
        val UNIT_Z = Vector3(0.0, 0.0, 1.0)
        val INFINITY = Vector3(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
    }

    val xy get() = Vector2(x, y)
    val yx get() = Vector2(y, x)
    val zx get() = Vector2(z, x)
    val xz get() = Vector2(x, z)

    /** Returns a normalized version of the vector. (i.e. unit vector) */
    val normalized: Vector3
        get() {
            val l = 1.0 / length
            if (l.isNaN() || l.isInfinite()) {
                return ZERO
            }
            return this * l
        }

    infix fun reflect(surfaceNormal: Vector3) = this - surfaceNormal * (this dot surfaceNormal) * 2.0

    operator fun get(i: Int): Double {
        return when (i) {
            0 -> x
            1 -> y
            2 -> z
            else -> throw RuntimeException("unsupported index")
        }
    }

    operator fun unaryMinus() = Vector3(-x, -y, -z)
    operator fun plus(v: Vector3) = Vector3(x + v.x, y + v.y, z + v.z)
    operator fun plus(d: Double) = Vector3(x + d, y + d, z + d)
    operator fun minus(v: Vector3) = Vector3(x - v.x, y - v.y, z - v.z)
    operator fun minus(d: Double) = Vector3(x - d, y - d, z - d)
    operator fun times(v: Vector3) = Vector3(x * v.x, y * v.y, z * v.z)
    operator fun times(s: Double) = Vector3(x * s, y * s, z * s)
    operator fun div(s: Double) = Vector3(x / s, y / s, z / s)
    operator fun div(v: Vector3) = Vector3(x / v.x, y / v.y, z / v.z)

    /** Calculates a dot product between this [Vector2] and [v]. */
    infix fun dot(v: Vector3) = x * v.x + y * v.y + z * v.z

    /** Calculates a cross product between this [Vector2] and [v]. */
    infix fun cross(v: Vector3) = Vector3(
        y * v.z - z * v.y,
        -(x * v.z - z * v.x),
        x * v.y - y * v.x)

    infix fun projectedOn(v: Vector3) = (this dot v) / (v dot v) * v

    /** The Euclidean length of the vector. */
    val length: Double get() = sqrt(x * x + y * y + z * z)

    /** The squared Euclidean length of the vector. */
    val squaredLength get() = x * x + y * y + z * z

    /** Casts to [DoubleArray]. */
    fun toDoubleArray() = doubleArrayOf(x, y, z)

    /** Calculates the Euclidean distance to [other]. */
    fun distanceTo(other: Vector3): Double {
        val dx = other.x - x
        val dy = other.y - y
        val dz = other.z - z
        return sqrt(dx * dx + dy * dy + dz * dz)
    }

    /** Calculates the squared Euclidean distance to [other]. */
    fun squaredDistanceTo(other: Vector3): Double {
        val dx = other.x - x
        val dy = other.y - y
        val dz = other.z - z
        return dx * dx + dy * dy + dz * dz
    }

    fun mix(o: Vector3, mix: Double): Vector3 = this * (1 - mix) + o * mix
}

operator fun Double.times(v: Vector3) = v * this

fun min(a: Vector3, b: Vector3) = Vector3(min(a.x, b.x), min(a.y, b.y), min(a.z, b.z))
fun max(a: Vector3, b: Vector3) = Vector3(max(a.x, b.x), max(a.y, b.y), max(a.z, b.z))

fun mix(a: Vector3, b: Vector3, mix:Double): Vector3 = a * (1 - mix) + b * mix
