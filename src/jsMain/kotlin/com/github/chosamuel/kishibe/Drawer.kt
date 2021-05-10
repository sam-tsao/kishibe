package com.github.chosamuel.kishibe


import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.TouchEvent
import org.w3c.dom.events.Event
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.get

fun Drawer(init: Drawer.() -> Unit) = Drawer().apply { init()}

class Drawer() {
    //CANVAS
    var width: Int = window.innerWidth
    var height: Int = window.innerHeight
    val canvas = document.createElement("canvas") as HTMLCanvasElement
    val ctx = canvas.getContext("2d", "alpha: false") as CanvasRenderingContext2D

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

    var onClick = {
    }

    var onRelease = {}

    init {
        initListeners()
    }

    fun initListeners(){
        window.addEventListener("mousemove",{
            val event = it as MouseEvent
            mouseX = event.clientX * 1.0
            mouseY = event.clientY * 1.0
        })
        canvas.addEventListener("mousedown", { onClick() })
        window.addEventListener("touchmove", {
            val event = it as TouchEvent
            mouseX = event.touches.get(0)!!.clientX * 1.0
            mouseY = event.touches.get(0)!!.clientY * 1.0
        })
        window.addEventListener("touchstart", {
            val event = it as TouchEvent
            mouseX = event.touches.get(0)!!.clientX * 1.0
            mouseY = event.touches.get(0)!!.clientY * 1.0
        })
        canvas.addEventListener("touchstart",{onClick()})

        canvas.addEventListener("mouseup", { onRelease()})
        canvas.addEventListener("touchend", { onRelease()})
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