package com.github.chosamuel.kishibe.math

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

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

data class Polar(val theta: Double, val radius: Double = 1.0){

    companion object {
        /** Constructs equivalent polar coordinates from the Cartesian coordinate system. */
        fun fromVector(vector: Vector2): Polar {
            val r = vector.length
            return Polar(
                if (r == 0.0) 0.0 else atan2(vector.y, vector.x).asDegrees,
                r
            )
        }
    }

    /** Constructs equivalent Cartesian coordinates from the polar representation. */
    val cartesian: Vector2
        get() {
            val theta = theta.asRadians
            val x = cos(theta)
            val y = sin(theta)
            return Vector2(x, y) * radius
        }

    operator fun plus(s: Polar) = Polar(theta + s.theta, radius + s.radius)
    operator fun minus(s: Polar) = Polar(theta - s.theta, radius - s.radius)
    operator fun times(s: Polar) = Polar(theta * s.theta, radius * s.radius)

    operator fun times(s: Double) = Polar(theta * s, radius * s)
    operator fun div(s: Double) = Polar(theta / s, radius / s)
}