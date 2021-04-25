package com.github.chosamuel.kishibe

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

    init {
        document.body?.appendChild(canvas)
        canvas.width = width
        canvas.height = height
    }

    fun draw(drawFunction:()->Unit){
        drawLoop = drawFunction
    }

    fun drawCall(){
        drawLoop()
    }

    fun start() {
        drawCall()
        frameCount++

        val myFunc = { it: Double ->
            start()
        }
        window.requestAnimationFrame(myFunc)
    }
}