package com.github.chosamuel.kishibe.application

import com.github.chosamuel.kishibe.application.renderer.Color
import com.github.chosamuel.kishibe.webgl.Shader
import kotlinx.browser.document
import kotlinx.browser.window
import org.khronos.webgl.WebGLContextAttributes
import org.khronos.webgl.WebGLRenderingContext as GL
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.TouchEvent
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.get

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
        initListeners()
        renderScene()
    }

    fun draw() = drawFunction()

    fun draw(fn: ()->Unit){
        drawFunction = fn
    }

    var onClick = {
    }

    var onRelease = {}

    fun initListeners(){
        window.addEventListener("mousemove",{
            val event = it as MouseEvent
            mouseX = event.clientX * 1.0
            mouseY = event.clientY * 1.0
        })
        canvas.addEventListener("mousedown", { onClick() })
        window.addEventListener("touchstart", {
            val event = it as TouchEvent
            mouseX = event.touches.get(0)!!.clientX * 1.0
            mouseY = event.touches.get(0)!!.clientY * 1.0
        })
        canvas.addEventListener("touchstart",{onClick()})

        canvas.addEventListener("mouseup", { onRelease()})
        canvas.addEventListener("touchend", { onRelease()})
    }


    fun renderScene() {
        draw()
        frameCount++
        window.requestAnimationFrame {
            renderScene()
        }
    }
}