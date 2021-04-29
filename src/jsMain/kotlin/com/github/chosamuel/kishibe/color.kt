package com.github.chosamuel.kishibe

import kotlin.math.PI
import kotlin.math.cos

fun cosineColor(
    t: Double,
    a: List<Double>,
    b: List<Double>,
    c: List<Double>,
    d: List<Double>,
):List<Double> {
    val color = listOf(
        (a[0] + b[0] * cos(PI*2 * (c[0]*t+d[0])))*255,
        (a[1]+ b[1] * cos(PI*2 * (c[1]*t+d[1])))*255,
        (a[2] + b[2] * cos(PI*2 * (c[2]*t+d[2])))*255
    );
    return color
}