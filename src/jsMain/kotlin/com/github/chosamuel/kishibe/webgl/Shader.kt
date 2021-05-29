package com.github.chosamuel.kishibe.webgl

import com.github.chosamuel.kishibe.application.Application
import com.github.chosamuel.kishibe.application.getHeight
import com.github.chosamuel.kishibe.application.getWidth
import org.khronos.webgl.WebGLProgram
import org.khronos.webgl.WebGLRenderingContext as GL
import org.khronos.webgl.WebGLShader

fun Application.createShader(
    vertexShaderSource: String = Shader.defaultVertexSource,
    fragmentShaderSource: String = Shader.defaultFragmentSource
): Shader = Shader(this, vertexShaderSource, fragmentShaderSource)

class Shader(
    val app: Application,
    val vsSource: String,
    val fsSource: String,
) {
    private var vertexShader = setupShaderFromSource(GL.VERTEX_SHADER,vsSource)
    private var fragmentShader = setupShaderFromSource(GL.FRAGMENT_SHADER,fsSource)
    var program = linkProgram()

    var positionAttributeLocation = app.gl.getAttribLocation(program, "position")
    var colorAttributeLocation = app.gl.getAttribLocation(program,"v_color")
    var vertexIdLocation = app.gl.getAttribLocation(program, "vertexID")
    var resolutionUniformLocation = app.gl.getUniformLocation(program, "resolution")
    var colorUniformLocation = app.gl.getUniformLocation(program, "color")
    var matrixLocation = app.gl.getUniformLocation(program, "matrix")

    companion object {
        val defaultVertexSource = """
        attribute vec4 position;
        attribute vec4 v_color;
        attribute float vertexID;
        
        uniform vec2 resolution;
        uniform vec4 color;
        uniform mat4 matrix;
        
        varying vec4 vertex_color;
        void main() {
          gl_Position = matrix * position;
         vertex_color = v_color;
        }    
        """.trimIndent()
        val defaultFragmentSource = """ 
        precision highp float;
        varying vec4 vertex_color;
        
        void main() {
          gl_FragColor = vertex_color;
        }
        """.trimIndent()
    }

    fun linkProgram(): WebGLProgram? {
        val program = app.gl.createProgram()
        app.gl.attachShader(program, vertexShader)
        app.gl.attachShader(program, fragmentShader)
        app.gl.linkProgram(program)
        val success = app.gl.getProgramParameter(program, org.khronos.webgl.WebGLRenderingContext.LINK_STATUS) as Boolean
        if(!success){
            console.log("failed to create shader program")
        }
        return program
    }

    fun setupShaderFromSource(shaderType: Int, shaderSource: String):WebGLShader?{
        val shader = app.gl.createShader(shaderType)
        app.gl.shaderSource(shader, shaderSource)
        app.gl.compileShader(shader)
        val success = app.gl.getShaderParameter(shader, GL.COMPILE_STATUS) as Boolean
        if(!success){
            console.log("Failed to compile shader")
            console.log(app.gl.getShaderInfoLog(shader))
        }

        when(shaderType){
            GL.FRAGMENT_SHADER -> fragmentShader = shader
            GL.VERTEX_SHADER -> vertexShader = shader
            else -> console.log("Invalid shader type")
        }
        return shader
    }

    fun bindDefaults(){
        app.gl.uniform2f(
            resolutionUniformLocation,
            app.getWidth().toFloat(),
            app.getHeight().toFloat()
        )
        app.gl.uniform4f(colorUniformLocation,
            app.fillColor.r.toFloat(),
            app.fillColor.g.toFloat(),
            app.fillColor.b.toFloat(),
            app.fillColor.a.toFloat()
        )
        //matrix
        //TODO Refactor matrix
        val w = app.getWidth().toFloat()
        val h = app.getHeight().toFloat()
        val d = 400f
        //ortho matrix
        val matrix = arrayOf(
            2f / w, 0f, 0f, 0f,
            0f, -2f/ h, 0f, 0f,
            0f, 0f, 2f / d, 0f,
            -1f,  1f,  0f,  1f
        )
        app.gl.uniformMatrix4fv(matrixLocation,false, matrix)
        app.gl.viewport(0,0,app.gl.canvas.width,app.gl.canvas.height)
    }

    fun use(){
        app.gl.useProgram(program)
        bindDefaults()
    }
    fun setUniform2f(name: String, f1: Float, f2: Float){
        app.gl.uniform2f(
            app.gl.getUniformLocation(program, name),
            f1,
            f2
        )
    }

    fun setUniform1f(name: String, float: Float){
        app.gl.uniform1f(app.gl.getUniformLocation(program,name),float)
    }

}