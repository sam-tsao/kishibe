package com.github.chosamuel.kishibe.ui


import com.github.chosamuel.kishibe.application.mouseX
import com.github.chosamuel.kishibe.application.mouseY
import com.github.chosamuel.kishibe.math.map
import com.github.chosamuel.kishibe.svg.*
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.svg.SVGElement
import kotlin.math.abs

fun XYPad(init: XYPad.() -> Unit) = XYPad().apply { init() }
class XYPad() : BaseUI() {
    var lengthPadding = width * 0.08
    var heightPadding = width * 0.05
    var strokeWidth = 0.5
    val xStart = lengthPadding
    val xEnd = width - lengthPadding
    val yStart = lengthPadding
    val yEnd = height - lengthPadding
    var easedMouseX = 0.5
    var easedMouseY = 0.5
    var dx = 0.0
    var dy = 0.0
    var xPos = 0.0
    var yPos = 0.0
    var easingRate = 0.05
    var xSlider: SVGElement
    var ySlider: SVGElement
    var lineRadius = 3
    var lineStrokeWidth = 10.0
    var x = 0.5
    var y = 0.5

    init {
        val yBar = lineSVG(heightPadding,lengthPadding,heightPadding,height-lengthPadding)
        yBar.setStroke(255.0,255.0,255.0,0.3)
        yBar.setStrokeWidth(strokeWidth)
        UI.appendChild(yBar)

        val xBar = lineSVG(lengthPadding, height-heightPadding, width-lengthPadding, height-heightPadding)
        xBar.setStroke(255.0,255.0,255.0,0.3)
        xBar.setStrokeWidth(strokeWidth)
        UI.appendChild(xBar)

        xSlider = document.createElementNS(svgns,"line") as SVGElement
        xSlider.setStrokeWidth(lineStrokeWidth)
        xSlider.setAttribute("stroke-linecap","round")
        UI.appendChild(xSlider)

        ySlider = document.createElementNS(svgns,"line") as SVGElement
        ySlider.setStrokeWidth(lineStrokeWidth)
        ySlider.setAttribute("stroke-linecap","round")
        UI.appendChild(ySlider)

        listen()
    }

    fun listen(){
        updateDifference()
        easeMouseValues()
        mapMouseToPos()
        updateValues()
        updateSliders()

        window.requestAnimationFrame {
            listen()
        }
    }
    fun updateDifference(){
        dx = mouseX - easedMouseX
        dy = mouseY - easedMouseY
    }
    fun easeMouseValues(){
        easedMouseX += (dx) * easingRate
        easedMouseY += (dy) * easingRate
    }
    fun mapMouseToPos(){
        xPos = map(0.0,width*1.0,xStart,xEnd,easedMouseX)
        yPos = map(0.0,height*1.0,yStart,yEnd,easedMouseY)
    }
    fun updateValues(){
        x = easedMouseX / width
        y = easedMouseY / height
    }
    fun updateSliders(){
        xSlider.setLinePoints(
            xPos - lineRadius, height-heightPadding,
            xPos+lineRadius, height-heightPadding
        )
        xSlider.setStroke(255.0,255.0,255.0,abs(dx)/(width*0.2)+0.05)

        ySlider.setLinePoints(
            heightPadding, yPos-lineRadius,
            heightPadding, yPos+lineRadius
        )
        ySlider.setStroke(255.0,255.0,255.0,abs(dy)/(height*0.2)+0.05)
    }

}