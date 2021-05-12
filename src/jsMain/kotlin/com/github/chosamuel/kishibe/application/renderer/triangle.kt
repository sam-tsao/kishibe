package com.github.chosamuel.kishibe.application.renderer


import com.github.chosamuel.kishibe.application.Application
import com.github.chosamuel.kishibe.application.worldToClip
import com.github.chosamuel.kishibe.math.Vector2
import com.github.chosamuel.kishibe.webgl.VertexBuffer
import org.khronos.webgl.WebGLRenderingContext as GL

//todo reimplement with mesh

fun Application.triangle(
    x1:Double,
    y1:Double,
    x2:Double,
    y2:Double,
    x3:Double,
    y3:Double
){
    val vbo = VertexBuffer(gl)
    vbo.addVertex(
        Vector2(
            worldToClip(x1,y1)[0].toDouble(),
            worldToClip(x1,y1)[1].toDouble()
        )
    )
    vbo.addVertex(
        Vector2(
            worldToClip(x2,y2)[0].toDouble(),
            worldToClip(x2,y2)[1].toDouble()
        )
    )
    vbo.addVertex(
        Vector2(
            worldToClip(x3,y3)[0].toDouble(),
            worldToClip(x3,y3)[1].toDouble()
        )
    )
    vbo.bind(this)
    vbo.draw(GL.TRIANGLES)
    vbo.unbind()

}