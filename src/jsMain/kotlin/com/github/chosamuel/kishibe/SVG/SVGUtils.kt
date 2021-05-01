package com.github.chosamuel.kishibe.SVG

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




