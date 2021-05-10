package com.github.chosamuel.kishibe.application.renderer

import com.github.chosamuel.kishibe.application.Application
import org.khronos.webgl.WebGLRenderingContext as GL

fun Application.fill(r: Double, g: Double, b: Double){
    fillColor = Color(r,g,b)
    gl.uniform4f(colorUniformLocation,
        fillColor.r.toFloat(),
        fillColor.g.toFloat(),
        fillColor.b.toFloat(),
        fillColor.a.toFloat()
    )
}

fun Application.clear(
    r: Double = 1.0,
    g: Double = 1.0,
    b: Double = 1.0,
    a: Double = 1.0
){
    gl.clearColor(r.toFloat(),g.toFloat(),b.toFloat(), a.toFloat())
    gl.clear(GL.COLOR_BUFFER_BIT)
}
fun Application.clear(color: Color) = clear(color.r,color.g,color.b,color.a)
fun Application.clear(grayscale: Double = 1.0) = clear(grayscale,grayscale,grayscale)
fun Application.clear(grayscale: Double = 1.0, alpha: Double= 1.0) =
    clear(grayscale,grayscale,grayscale,alpha)

