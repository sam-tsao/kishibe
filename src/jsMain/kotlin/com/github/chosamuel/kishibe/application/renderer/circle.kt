package com.github.chosamuel.kishibe.application.renderer

import com.github.chosamuel.kishibe.application.Application
import com.github.chosamuel.kishibe.math.Vector2
import com.github.chosamuel.kishibe.webgl.VertexBuffer
import org.khronos.webgl.WebGLRenderingContext as GL
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun Application.circle(
    x: Double,
    y: Double,
    r: Double,
    resolution: Int = 90
){
    val vbo = VertexBuffer(gl)
    vbo.addVertex(Vector2(x,y))
    for(i in 0..resolution){
        val step = (2*PI)  / (resolution * 1.0)
        val pt = Vector2(
            cos(step*i) * r + x,
            sin(step*i) * r + y
        )
        vbo.addVertex(pt)
    }
    vbo.bind(this)
    vbo.draw(GL.TRIANGLE_FAN)
    vbo.unbind()

}