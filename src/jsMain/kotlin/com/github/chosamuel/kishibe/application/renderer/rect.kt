package com.github.chosamuel.kishibe.application.renderer

import com.github.chosamuel.kishibe.application.Application
import com.github.chosamuel.kishibe.graphics.createMesh
import com.github.chosamuel.kishibe.math.Vector2
import org.khronos.webgl.WebGLRenderingContext as GL

fun Application.rect(
    topLeft: Vector2,
    width: Double,
    height: Double
){
    val m = createMesh()
    //VERTICES
    m.addVertex(topLeft)
    m.addVertex(topLeft.plus(Vector2(width,0.0)))
    m.addVertex(topLeft.plus(Vector2(0.0,height)))
    m.addVertex(topLeft.plus(Vector2(width,height)))
    repeat(4){
        m.addColor(fillColor)
    }
    //INDICES
    m.addIndex(0.toShort())
    m.addIndex(1.toShort())
    m.addIndex(2.toShort())

    m.addIndex(1.toShort())
    m.addIndex(3.toShort())
    m.addIndex(2.toShort())
    m.draw(GL.TRIANGLES)
}

fun Application.rect(
    x: Double,
    y: Double,
    width: Double,
    height: Double
) = rect(Vector2(x,y),width,height)