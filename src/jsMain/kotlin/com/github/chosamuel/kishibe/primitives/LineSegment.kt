package com.github.chosamuel.kishibe.primitives

import com.github.chosamuel.kishibe.math.Vec2

data class LineSegment(val start: Vec2, val end: Vec2) {
    constructor(x0: Double, y0: Double, x1: Double, y1: Double) :
            this(Vec2(x0, y0), Vec2(x1, y1))
}