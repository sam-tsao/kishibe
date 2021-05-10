package com.github.chosamuel.kishibe.primitives


import com.github.chosamuel.kishibe.math.Vector2

data class Circle(val center: Vector2, val radius: Double) {
    constructor(x: Double, y: Double, radius: Double) : this(Vector2(x, y), radius)

    fun intersects(other: Circle): Boolean {
        val d = this.center.distanceTo(other.center)
        return d < (this.radius + other.radius)
    }

}