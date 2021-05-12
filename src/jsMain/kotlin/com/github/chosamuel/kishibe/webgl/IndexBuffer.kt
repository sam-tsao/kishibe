package com.github.chosamuel.kishibe.webgl

import com.github.chosamuel.kishibe.application.Application
import org.khronos.webgl.Uint16Array
import org.khronos.webgl.WebGLRenderingContext as GL

class IndexBuffer(val glContext: GL): Buffer {
    val indices = mutableListOf<Short>()
    private var indexBuffer = glContext.createBuffer()

    fun addIndex(i: Short) = indices.add(i)
    fun addIndices(l: List<Short>){
        l.forEach {
           indices.add(it)
        }
    }
    fun numIndices(): Int = indices.size

    override fun bind(app: Application){
        val indexData = Uint16Array(indices.toTypedArray())
        indexBuffer = glContext.createBuffer()
        glContext.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, indexBuffer)
        glContext.bufferData(GL.ELEMENT_ARRAY_BUFFER,indexData,GL.STATIC_DRAW)
    }

    fun draw(glPrimitiveMode: Int) = glContext.drawElements(
        glPrimitiveMode,
        numIndices(),
        GL.UNSIGNED_SHORT,
        0
    )
    override fun unbind() = glContext.deleteBuffer(indexBuffer)

}