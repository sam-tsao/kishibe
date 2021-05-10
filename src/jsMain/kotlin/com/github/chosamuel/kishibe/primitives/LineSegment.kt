package com.github.chosamuel.kishibe.primitives

import com.github.chosamuel.kishibe.math.Vector2

data class LineSegment(val start: Vector2, val end: Vector2) {
    constructor(x0: Double, y0: Double, x1: Double, y1: Double) :
            this(Vector2(x0, y0), Vector2(x1, y1))
}