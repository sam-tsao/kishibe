package com.github.chosamuel.kishibe.color

import kotlin.math.PI
import kotlin.math.cos

fun cosineColor(
    t: Double,
    a: List<Double> = listOf(0.5,0.5,0.5),
    b: List<Double> = listOf(0.5,0.5,0.5),
    c: List<Double> = listOf(1.0,0.7,0.4),
    d: List<Double> = listOf(0.0,0.15,0.2),
):List<Double> {
    val color = listOf(
        (a[0] + b[0] * cos(PI*2 * (c[0]*t+d[0]))),
        (a[1]+ b[1] * cos(PI*2 * (c[1]*t+d[1]))),
        (a[2] + b[2] * cos(PI*2 * (c[2]*t+d[2])))
    );
    return color
}