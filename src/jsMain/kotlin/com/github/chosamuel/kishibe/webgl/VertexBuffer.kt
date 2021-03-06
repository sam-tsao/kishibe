package com.github.chosamuel.kishibe.webgl

import com.github.chosamuel.kishibe.application.Application
import com.github.chosamuel.kishibe.math.Vector2
import com.github.chosamuel.kishibe.math.Vector3
import org.khronos.webgl.WebGLRenderingContext as GL
import org.khronos.webgl.Float32Array
import org.khronos.webgl.set

class VertexBuffer(val glContext: GL) {
    val vertices = mutableListOf<Vector3>()
    private var positionBuffer = glContext.createBuffer()

    fun addVertex(v: Vector2) = vertices.add(Vector3(v.x,v.y,0.0))
    fun addVertices(l: List<Vector2>){
        l.forEach {
            vertices.add(Vector3(it.x,it.y,0.0))
        }
    }

    fun numVertices():Int = vertices.size

    fun bind(app: Application){
        //CREATE DATA
        val positions = Float32Array(vertices)

        val vertexIds = Float32Array(numVertices());
        for(i in 0 until numVertices()){
            vertexIds[i] = i.toFloat()
        }

        //BIND DATA
        glContext.enableVertexAttribArray(app.activeShader.positionAttributeLocation)
        positionBuffer = glContext.createBuffer()
        glContext.bindBuffer(GL.ARRAY_BUFFER, positionBuffer)
        glContext.bufferData(GL.ARRAY_BUFFER, positions, GL.STATIC_DRAW)
        glContext.vertexAttribPointer(
            app.activeShader.positionAttributeLocation,
            3,
            GL.FLOAT,
            false,
            0,
            0
        )
    }

    fun draw(glPrimitiveMode: Int){
        glContext.drawArrays(glPrimitiveMode,0,numVertices())
    }

    fun unbind() = glContext.deleteBuffer(positionBuffer)

}