package com.github.chosamuel.kishibe.ui

import com.github.chosamuel.kishibe.svg.svgns
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.svg.SVGElement

open class BaseUI {
    val UI = document.createElementNS(svgns,"svg")
    var width = window.innerWidth
    var height = window.innerHeight
    var onClickOuter = {}

    init {
        UI.setAttribute("width","${width}")
        UI.setAttribute("height","${height}")
        UI.setAttribute("style","position: absolute;top:0;left:0;")
        /**
        UI currently blocks out canvas mouse events because it is full sized.
        So adding event listeners to the SVG block itself is a hack for now.
        Need to declare onclick lambda in drawer class for the UI.
        Perhaps in the future, use drawer class to append the UI and fill
        the onClick from there.
         */
        UI.addEventListener("mousedown",{
            val tgt = it.target as SVGElement
            if(!tgt.classList.contains("btn")){
                onClickOuter()
            }
        })
        UI.addEventListener("touchstart",{
            val tgt = it.target as SVGElement
            if(!tgt.classList.contains("btn")){
                onClickOuter()
            }
        })
    }
    fun setWidth(w: Double){
        UI.setAttribute("width","${w}")
    }
    fun setHeight(h: Double){
        UI.setAttribute("height","${h}")
    }
    fun setPosition(x: Double, y: Double){
        UI.setAttribute("style","position: absolute;top:$y;left:$x;")
    }

    fun appendToDocument(){
        document.body?.appendChild(UI)
    }
}