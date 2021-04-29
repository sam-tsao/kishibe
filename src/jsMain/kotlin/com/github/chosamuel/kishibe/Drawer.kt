package com.github.chosamuel.kishibe


import com.github.chosamuel.kishibe.math.Vec2
import com.github.chosamuel.kishibe.noise.OpenSimplexNoise
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

    //GLOBAL VARIABLES
    var frameCount = 0
    var mouseX: Double = 0.0
    var mouseY: Double = 0.0
    val mouse: Vec2
        get(){
            return Vec2(mouseX, mouseY)
        }

    //WRAPPERS
    val n = OpenSimplexNoise()

    //HOOKS
    var setupBlock ={}
    var drawLoop = {}

    init {
        window.addEventListener("mousemove",{
            val event = it as MouseEvent
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