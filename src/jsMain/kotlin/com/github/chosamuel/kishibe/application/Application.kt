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
    var defaultShader = Shader( this)
    var isSetup = true
    var frameCount = 0
    var timer = Timer()

    var positionAttributeLocation = gl.getAttribLocation(defaultShader.program, "position")
    var colorAttributeLocation = gl.getAttribLocation(defaultShader.program,"v_color")
    var resolutionUniformLocation = gl.getUniformLocation(defaultShader.program, "resolution")
    var colorUniformLocation = gl.getUniformLocation(defaultShader.program, "color")
    var matrixLocation = gl.getUniformLocation(defaultShader.program, "matrix")
    var vertexIDLocation = gl.getAttribLocation(defaultShader.program, "vertexID")

    var drawFunction = {}
    var setupFunction = {}

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
    fun setActiveShader(vs: String, fs: String){
        defaultShader = Shader(this,vs,fs)
        positionAttributeLocation = gl.getAttribLocation(defaultShader.program, "position")
        colorAttributeLocation = gl.getAttribLocation(defaultShader.program,"v_color")
        resolutionUniformLocation = gl.getUniformLocation(defaultShader.program, "resolution")
        colorUniformLocation = gl.getUniformLocation(defaultShader.program, "color")
        matrixLocation = gl.getUniformLocation(defaultShader.program, "matrix")
        defaultShader.use()
    }

    fun draw() = drawFunction()

    fun draw(fn: ()->Unit){
        drawFunction = fn
    }
    fun setup(fn: ()->Unit){
        setupFunction = fn
        isSetup = false
    }

    var onClick = {}
    var onDragged = {}
    var onRelease = {}
    var mouseClicked = false

    fun initListeners(){
        window.addEventListener("mousemove",{
            val event = it as MouseEvent
            mouseX = event.clientX * 1.0
            mouseY = event.clientY * 1.0
            if(mouseClicked){ onDragged() }
        })

        canvas.addEventListener("mousedown", {
            mouseClicked = true
            onClick()
        })

        window.addEventListener("touchmove", {
            val event = it as TouchEvent
            mouseX = event.touches.get(0)!!.clientX * 1.0
            mouseY = event.touches.get(0)!!.clientY * 1.0
            if(mouseClicked){ onDragged() }
        })

        canvas.addEventListener("touchstart",{
            val event = it as TouchEvent
            mouseX = event.touches.get(0)!!.clientX * 1.0
            mouseY = event.touches.get(0)!!.clientY * 1.0
            onClick()
            mouseClicked = true
        })

        canvas.addEventListener("mouseup", {
            mouseClicked = false
            onRelease()
        })
        canvas.addEventListener("touchend", {
            mouseClicked = false
            onRelease()
        })
        canvas.addEventListener("touchcancel", {
            mouseClicked = false
            onRelease()
        })
    }


    fun renderScene() {
        if(!isSetup){
            setupFunction()
            isSetup = true
        }
        draw()
        frameCount++
        window.requestAnimationFrame {
            renderScene()
        }
    }
}