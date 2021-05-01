package com.github.chosamuel.kishibe


import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.MouseEvent

fun Drawer(init: Drawer.() -> Unit) = Drawer().apply { init()}

class Drawer() {
    //CANVAS
    var width: Int = window.innerWidth
    var height: Int = window.innerHeight
    val canvas = document.createElement("canvas") as HTMLCanvasElement
    val ctx = canvas.getContext("2d") as CanvasRenderingContext2D

    //INIT VARIABLES
    var setupDone = false

    //STYLE VARIABLES
    var useFill = true
    var lineWidth = 1.0
    var useStroke = false
    var strokeStyle = "black"
    var fillStyle = "white"

    //HOOKS
    var setupBlock ={}
    var drawLoop = {}

    init {
        initWindowListeners()
    }

    fun initWindowListeners(){
        window.addEventListener("mousemove",{
            val event = it as MouseEvent
            pMouseX = mouseX
            pMouseY = mouseY
            mouseX = event.clientX * 1.0
            mouseY = event.clientY * 1.0
        })
    }

    fun attach(){
        canvas.setAttribute("width","$width")
        canvas.setAttribute("height","$height")
        canvas.setAttribute("id","kishibe-canvas")
        document.body?.appendChild(canvas)
    }

    fun setup(setupFunction:()->Unit){
        setupBlock = setupFunction
    }

    fun draw(drawFunction:()->Unit){
        drawLoop = drawFunction
    }

    fun start() {
        if(!setupDone){
            attach()
            setupBlock()
            setupDone = true
        }
        drawLoop()
        frameCount++
        window.requestAnimationFrame{
            start()
        }
    }
}