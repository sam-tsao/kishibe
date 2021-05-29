package com.github.chosamuel.kishibe.webgl
import com.github.chosamuel.kishibe.application.Application
import org.khronos.webgl.WebGLRenderingContext as GL

class idBuffer(val app: Application): Buffer {
    val ids = mutableListOf<Float>()
    var idBuffer = app.gl.createBuffer()

    override fun bind(app: Application){
        val idData = ids.toTypedArray()
        app.gl.run {
            enableVertexAttribArray(app.activeShader.vertexIdLocation)
            idBuffer = createBuffer()
            bindBuffer(GL.ARRAY_BUFFER, idBuffer)
            bufferData(GL.ARRAY_BUFFER, org.khronos.webgl.Float32Array(idData), GL.STATIC_DRAW)
            vertexAttribPointer(
                app.activeShader.vertexIdLocation,
                1,
                GL.FLOAT,
                false,
                0,
                0
            )
        }
    }
    override fun unbind(){
        app.gl.deleteBuffer(idBuffer)
    }

    fun setup(numVertices: Int){
        repeat(numVertices){
            ids.add(it.toFloat())
        }
    }
}