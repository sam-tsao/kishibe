package com.github.chosamuel.kishibe.webgl

import com.github.chosamuel.kishibe.application.Application
import com.github.chosamuel.kishibe.application.getHeight
import com.github.chosamuel.kishibe.application.getWidth
import org.khronos.webgl.WebGLProgram
import org.khronos.webgl.WebGLRenderingContext as GL
import org.khronos.webgl.WebGLShader

class Shader(
    val glContext: GL,
    val app: Application
) {
    private var vertexShader = setupShaderFromSource(GL.VERTEX_SHADER,
        """
                attribute vec4 position;
                uniform vec2 resolution;
                uniform vec4 color;
                varying vec4 v_color;

                void main() {
                
                  vec2 zeroToOne = position.xy / resolution;
                  vec2 zeroToTwo = zeroToOne * 2.0;
                  vec2 clipSpace = zeroToTwo - 1.0;
                  gl_Position = vec4(clipSpace * vec2(1,-1), 0, 1);
                  v_color = color;
                } 
                """)

    private var fragmentShader = setupShaderFromSource(GL.FRAGMENT_SHADER,
        """
                         precision highp float;
                         varying vec4 v_color;
                         
                         void main() {
                           gl_FragColor = v_color;
                         }
                        """
    )
    var program = linkProgram()

    fun linkProgram(): WebGLProgram? {
        val program = glContext.createProgram()
        glContext.attachShader(program, vertexShader)
        glContext.attachShader(program, fragmentShader)
        glContext.linkProgram(program)
        val success = glContext.getProgramParameter(program, org.khronos.webgl.WebGLRenderingContext.LINK_STATUS) as Boolean
        if(!success){
            console.log("failed to create shader program")
        }
        return program
    }

    fun setupShaderFromSource(shaderType: Int, shaderSource: String):WebGLShader?{
        val shader = glContext.createShader(shaderType)
        glContext.shaderSource(shader, shaderSource)
        glContext.compileShader(shader)
        val success = glContext.getShaderParameter(shader, GL.COMPILE_STATUS) as Boolean
        if(!success){
            console.log("Failed to compile shader")
            console.log(glContext.getShaderInfoLog(shader))
        }

        when(shaderType){
            GL.FRAGMENT_SHADER -> fragmentShader = shader
            GL.VERTEX_SHADER -> vertexShader = shader
            else -> console.log("Invalid shader type")
        }
        return shader
    }

    fun bindDefaults(){
        glContext.uniform2f(
            app.resolutionUniformLocation,
            app.getWidth().toFloat(),
            app.getHeight().toFloat()
        )
        glContext.uniform4f(app.colorUniformLocation,
            app.fillColor.r.toFloat(),
            app.fillColor.g.toFloat(),
            app.fillColor.b.toFloat(),
            app.fillColor.a.toFloat()
        )
        glContext.viewport(0,0,glContext.canvas.width,glContext.canvas.height)
    }

    fun use(){
        glContext.useProgram(program)
        bindDefaults()
    }

}