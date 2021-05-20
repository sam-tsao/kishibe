package com.github.chosamuel.kishibe.application.renderer

import com.github.chosamuel.kishibe.application.Application
import com.github.chosamuel.kishibe.graphics.Mesh
import com.github.chosamuel.kishibe.math.Vector2
import org.khronos.webgl.WebGLRenderingContext as GL
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun Application.circle(
    x: Double,
    y: Double,
    r: Double,
    resolution: Int = 120
){
    val m = Mesh(gl,this)
    m.addVertex(Vector2(x,y))
    m.addColor(fillColor)
    m.addIndex(0)
    repeat(resolution+1){ i->
        val step = (2*PI)  / (resolution * 1.0)
        val pt = Vector2(
            cos(step*i) * r + x,
            sin(step*i) * r + y
        )
        m.addVertex(pt)
        m.addColor(fillColor)
        m.addIndex((i+1).toShort())
    }
    m.draw(GL.TRIANGLE_FAN)
}

fun Application.circle(center: Vector2, r: Double, resolution: Int = 120){
    circle(center.x,center.y,r,resolution)
}