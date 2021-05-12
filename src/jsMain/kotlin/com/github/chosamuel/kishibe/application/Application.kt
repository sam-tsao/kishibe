package com.github.chosamuel.kishibe.application

import com.github.chosamuel.kishibe.application.renderer.Color
import com.github.chosamuel.kishibe.webgl.Shader
import kotlinx.browser.document
import kotlinx.browser.window
import org.khronos.webgl.WebGLContextAttributes
import org.khronos.webgl.WebGLRenderingContext as GL
import org.w3c.dom.HTMLCanvasElement

fun Application(init: Application.()->Unit) = Application().apply { init() }



class Application {
    val canvas = document.createElement("canvas") as HTMLCanvasElement
    val gl = canvas.getContext("webgl") as GL
    val defaultShader = Shader( this)
    var frameCount = 0
    var timer = Timer()

    val positionAttributeLocation = gl.getAttribLocation(defaultShader.program, "position")
    val colorAttributeLocation = gl.getAttribLocation(defaultShader.program,"v_color")
    val resolutionUniformLocation = gl.getUniformLocation(defaultShader.program, "resolution")
    val colorUniformLocation = gl.getUniformLocation(defaultShader.program, "color")
    val matrixLocation = gl.getUniformLocation(defaultShader.program, "matrix")

    var drawFunction = {}

    //STYLES
    var fillColor = Color(1.0,1.0,1.0,1.0)

    init {
        canvas.width = window.innerWidth
        canvas.height =  window.innerHeight
        document.body?.appendChild(canvas)
        defaultShader.use()
        renderScene()
    }

    fun draw() = drawFunction()

    fun draw(fn: ()->Unit){
        drawFunction = fn
    }


    fun renderScene() {
        draw()
        frameCount++
        window.requestAnimationFrame {
            renderScene()
        }
    }
}