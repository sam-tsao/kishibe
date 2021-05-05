package com.github.chosamuel.kishibe.svg

import com.github.chosamuel.kishibe.svgns
import kotlinx.browser.document
import org.w3c.dom.svg.SVGElement

fun SVGElement.setWidth(width: Double) {
    this.setAttribute("width", "$width")
}

fun SVGElement.setHeight(height: Double) {
    this.setAttribute("height", "$height")
}

fun SVGElement.setID(id: String) {
    this.setAttribute("id", "$id")
}

fun SVGElement.addClass(elClass: String){
    this.classList.add(elClass)
}

fun SVGElement.addClasses(classes: List<String>){
    classes.forEach { c->
        this.classList.add(c)
    }
}

fun SVGElement.removeClass(elClass: String){
    this.classList.remove(elClass)
}

fun lineSVG(x1: Double, y1: Double, x2: Double, y2: Double):SVGElement{
    val line = document.createElementNS(svgns,"line") as SVGElement
    line.setAttribute("x1","$x1")
    line.setAttribute("y1","$y1")
    line.setAttribute("x2","$x2")
    line.setAttribute("y2","$y2")
    return line
}

fun SVGElement.setStroke(r:Double, g:Double,b:Double,a:Double){
    this.setAttribute("stroke","rgba($r,$g,$b,$a)")
}

fun SVGElement.setStrokeWidth(strokeWidth: Double){
    this.setAttribute("stroke-width","$strokeWidth")
}

fun SVGElement.setFill(r:Double, g:Double,b:Double,a:Double){
    this.setAttribute("fill","rgba($r,$g,$b,$a)")
}
fun SVGElement.setLinePoints(x1: Double, y1: Double, x2: Double, y2: Double){
    this.setAttribute("x1","$x1")
    this.setAttribute("y1","$y1")
    this.setAttribute("x2","$x2")
    this.setAttribute("y2","$y2")
}




