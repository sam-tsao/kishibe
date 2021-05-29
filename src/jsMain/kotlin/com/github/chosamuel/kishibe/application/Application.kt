package com.github.chosamuel.kishibe.application

import com.github.chosamuel.kishibe.application.renderer.Color
import com.github.chosamuel.kishibe.webgl.Shader
import com.github.chosamuel.kishibe.webgl.createShader
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
    var activeShader = createShader()
    var isSetup = false
    var frameCount = 0
    var timer = Timer()
    var drawProgram: Program = Program()

    //STYLES
    var fillColor = Color(1.0,1.0,1.0,1.0)

    init {
        canvas.width = window.innerWidth
        canvas.height =  window.innerHeight
        document.body?.appendChild(canvas)
        activeShader.use()
        initListeners()
        renderScene()
    }
    fun setActiveShader(shader: Shader){
        activeShader = shader
        activeShader.use()
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
            drawProgram.setup()
        }
        drawProgram.drawLoop()
        frameCount++
        window.requestAnimationFrame {
            renderScene()
        }
    }

    fun Program(init: Program.()->Unit){
        val p = Program()
        p.setup =  {
            p.init()
            this.isSetup = true
        }
        drawProgram = p
    }
    class Program {
        var setup = {}
        var drawLoop = {}
        fun Draw(fn: () -> Unit){
            drawLoop = fn
        }
    }

}