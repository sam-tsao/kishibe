package com.github.chosamuel.kishibe.graphics

import com.github.chosamuel.kishibe.application.Application
import com.github.chosamuel.kishibe.application.renderer.Color
import com.github.chosamuel.kishibe.math.Vector2
import com.github.chosamuel.kishibe.webgl.ColorBuffer
import com.github.chosamuel.kishibe.webgl.IndexBuffer
import com.github.chosamuel.kishibe.webgl.idBuffer
import com.github.chosamuel.kishibe.webgl.VertexBuffer

fun Application.createMesh():Mesh = Mesh(this)

/**
 * A basic mesh class for
 * creaing models.
 */
class Mesh(val app: Application) {
    var initialized = false
    var vBuffer =  VertexBuffer(app.gl)
    var cBuffer =  ColorBuffer(app.gl)
    var idBuffer = idBuffer(app)
    var iBuffer =  IndexBuffer(app.gl)

    fun addVertex(v: Vector2) = vBuffer.addVertex(v)
    fun addColor(c: Color) = cBuffer.addColor(c)
    fun addIndex(i: Short) = iBuffer.addIndex(i)
    fun initialize(){
        initialized = true
        if(cBuffer.numColors() == 0){
            repeat(vBuffer.numVertices()){
                cBuffer.addColor(app.fillColor)
            }
        }
        idBuffer.setup(vBuffer.numVertices())
    }

    fun draw(glPrimitiveMode:Int){
        initialize()
       // TODO(when cBuffer is less than vertices but greater than 0)
        vBuffer.bind(app)
        cBuffer.bind(app)
        idBuffer.bind(app)
        iBuffer.bind(app)

        iBuffer.draw(glPrimitiveMode)

        vBuffer.unbind()
        cBuffer.unbind()
        idBuffer.unbind()
        iBuffer.unbind()
    }
}