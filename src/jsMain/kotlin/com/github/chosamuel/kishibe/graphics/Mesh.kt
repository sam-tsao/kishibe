package com.github.chosamuel.kishibe.graphics

import com.github.chosamuel.kishibe.application.Application
import com.github.chosamuel.kishibe.application.renderer.Color
import com.github.chosamuel.kishibe.math.Vector2
import com.github.chosamuel.kishibe.webgl.ColorBuffer
import com.github.chosamuel.kishibe.webgl.IndexBuffer
import com.github.chosamuel.kishibe.webgl.VertexBuffer
import org.khronos.webgl.WebGLRenderingContext as GL

/**
 * A basic mesh class for
 * creaing models.
 */
class Mesh(val glContext: GL, val app: Application) {

    var vBuffer =  VertexBuffer(glContext)
    var cBuffer =  ColorBuffer(glContext)
    var iBuffer =  IndexBuffer(glContext)

    fun addVertex(v: Vector2) = vBuffer.addVertex(v)
    fun addColor(c: Color) = cBuffer.addColor(c)
    fun addIndex(i: Short) = iBuffer.addIndex(i)

    fun draw(glPrimitiveMode:Int){
        if(cBuffer.numColors() == 0){
            repeat(vBuffer.numVertices()){
                cBuffer.addColor(app.fillColor)
            }
        }
        // TODO(when cBuffer is less than vertices but greater than 0)
        vBuffer.bind(app)
        cBuffer.bind(app)
        iBuffer.bind(app)

        iBuffer.draw(glPrimitiveMode)

        vBuffer.unbind()
        cBuffer.unbind()
        iBuffer.unbind()
    }
}