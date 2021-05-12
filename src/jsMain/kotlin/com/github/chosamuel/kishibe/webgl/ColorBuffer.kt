package com.github.chosamuel.kishibe.webgl

import com.github.chosamuel.kishibe.application.Application
import com.github.chosamuel.kishibe.application.renderer.Color
import org.khronos.webgl.Float32Array
import kotlin.math.floor
import org.khronos.webgl.WebGLRenderingContext as GL

class ColorBuffer(val glContext: GL): Buffer{
    val colors = mutableListOf<Color>()
    var colorBuffer = glContext.createBuffer()

    override fun bind(app: Application) {
        val colorData = Array(colors.size*4){
            val index = floor(it/4.0).toInt()
            when(it%4){
                0 -> colors[index].r.toFloat()
                1 -> colors[index].g.toFloat()
                2 -> colors[index].b.toFloat()
                else -> colors[index].a.toFloat()
            }
        }
        val colorBuffer = glContext.createBuffer()
        glContext.bindBuffer(GL.ARRAY_BUFFER, colorBuffer)
        glContext.bufferData(GL.ARRAY_BUFFER, Float32Array(colorData),GL.STATIC_DRAW)
        glContext.vertexAttribPointer(
            app.colorAttributeLocation,
            4,
            GL.FLOAT,
            false,
            0,
            0
        )
        glContext.enableVertexAttribArray(app.colorAttributeLocation)
    }
    fun addColor(c: Color) = colors.add(c)

    fun addColors(l: List<Color>){
        l.forEach {
            colors.add(it)
        }
    }
    fun numColors():Int = colors.size
    override fun unbind() = glContext.deleteBuffer(colorBuffer)

}