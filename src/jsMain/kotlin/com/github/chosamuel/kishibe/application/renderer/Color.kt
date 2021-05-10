package com.github.chosamuel.kishibe.application.renderer

data class Color(
    val r: Double = 1.0,
    val g: Double = 1.0,
    val b: Double = 1.0,
    val a: Double = 1.0,
) {
    constructor(r: Double, g:Double,b:Double): this(r,g,b,1.0)
    constructor(grayscale: Double): this(grayscale,grayscale,grayscale,1.0)
    constructor(grayscale: Double,alpha: Double): this(grayscale,grayscale,grayscale,alpha)
}