package com.github.chosamuel.kishibe.ui

import kotlinx.browser.document
import kotlinx.browser.window
import com.github.chosamuel.kishibe.svg.addClasses
import com.github.chosamuel.kishibe.svg.addClass
import com.github.chosamuel.kishibe.svg.removeClass
import com.github.chosamuel.kishibe.svg.setHeight
import com.github.chosamuel.kishibe.svg.setID
import com.github.chosamuel.kishibe.svg.setWidth
import com.github.chosamuel.kishibe.svgns
import org.w3c.dom.svg.SVGElement
import kotlin.math.ceil

fun Radio(init: Radio.() -> Unit) = Radio().apply {
    init()
}

class Radio(
    var numBtns:Int = 3,
    var btnWidth:Double = 20.0,
    var btnHeight:Double = 20.0,
    var paddingWidth:Double = 30.0,
    var topPadding:Double = (window.innerHeight - (btnHeight*2.0))

) : BaseUI(){

    val UIWidth = numBtns * btnWidth + (numBtns-1) * paddingWidth
    val leftPadding = (window.innerWidth - UIWidth) * 0.5
    var mode = 0
    var onClick = {}

    init {

        for(i in 0 until numBtns){
            val btn = document.createElementNS(svgns, "rect") as SVGElement
            val btnx = leftPadding + (i * (btnWidth + paddingWidth))
            val id = "btn-${i+1}"
            btn.setWidth(btnWidth)
            btn.setHeight(btnHeight)
            btn.setID("$id")
            val defaultState = if(i==0) "active" else "inactive"
            btn.addClasses(listOf("btn",defaultState))
            btn.setAttribute("x", "$btnx")
            btn.setAttribute("y", "$topPadding")
            btn.addEventListener("mousedown", {
                val activeBtn = document.querySelector(".active") as SVGElement
                activeBtn.deactivate()
                btn.activate()
                mode = i
                onClick()
            })
            UI.appendChild(btn)
        }
        addCSSStyles()
    }

    fun SVGElement.deactivate(){
        this.removeClass("active")
        this.addClass("inactive")
    }

    fun SVGElement.activate(){
        this.removeClass("inactive")
        this.addClass("active")
    }

    fun addCSSStyles(){
        val styles = """
                
            .active {
                fill: rgba(211,211,211,0.8);
            }
            .inactive {
                fill: rgba(211,211,211,0.3);
            }
            .btn {
                transition: 0.2s;
            }
            .btn:hover {
                fill: rgba(211,211,211,0.8);
            }
            
            """

        val styleSheet = document.createElement("style")
        styleSheet.setAttribute("type","text/css")
        styleSheet.innerHTML += styles
        document.head?.appendChild(styleSheet)
    }

    //optional
    fun setOnClick(func:()->Unit){
        onClick = func
    }
}