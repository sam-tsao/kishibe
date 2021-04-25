package com.github.chosamuel.kishibe

import com.github.chosamuel.kishibe.noise.OpenSimplexNoise
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

fun drawer(init: Drawer.() -> Unit) = Drawer().apply { init()}

class Drawer(
    var width: Int = window.innerWidth,
    var height: Int = window.innerHeight,
) {
    val canvas = document.createElement("canvas") as HTMLCanvasElement
    val ctx = canvas.getContext("2d") as CanvasRenderingContext2D
    var frameCount = 0
    var drawLoop = {}
    var useFill = true
    var useStroke = false
    var strokeStyle = "black"
    var fillStyle = "white"
    val n = OpenSimplexNoise()

    init {
        document.body?.appendChild(canvas)
        canvas.width = width
        canvas.height = height
    }

    fun draw(drawFunction:()->Unit){
        drawLoop = drawFunction
    }

    fun start() {
        drawLoop()
        frameCount++

        window.requestAnimationFrame{
            start()
        }
    }
}