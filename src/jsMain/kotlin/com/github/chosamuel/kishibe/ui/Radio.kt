package com.github.chosamuel.kishibe.ui

import com.github.chosamuel.kishibe.application.renderer.Color
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.dom.addClass
import kotlinx.dom.removeClass
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.fetch.Body


class Radio(
    val numBtns: Int = 3
) {
    val btnSize: Int = 25
    val btnSpacing: Int = 30
    val radioWidth = (btnSize * numBtns) + (btnSpacing * (numBtns - 1))
    var activeColor = Color(0.8,0.8,0.8,0.8)
    var inactiveColor = Color(0.8,0.8,0.8,0.3)
    var mode = 0
    var onClick = {}

    fun attach(){
        console.log("new radio")
        val container = document.createElement("div")
        container.classList.add("radio-container")
        val leftPadding = calcLeftPadding()
        val topPadding = calcTopPadding()
        container.setAttribute("style","""
            width: ${radioWidth}px;
            height: ${btnSize}px;
            left: ${leftPadding}px;
            top: ${topPadding}px;
            position: fixed;
            display: flex;
            justify-content: space-between;
        """)
        //CREATE BUTTONS
        repeat(numBtns){ i->
            val btn = document.createElement("div")
            btn.classList.add("btn")
            btn.setAttribute("style","""
                width: ${btnSize}px;
                height: ${btnSize}px;
            """)
            val defaultState = if(i==0) "active" else "inactive"
            btn.addClass(defaultState)
            btn.addEventListener("mousedown", {
                val activeBtn = document.querySelector(".active")
                activeBtn?.deactivate()
                btn.activate()
                mode = i
                onClick()
            })
            container.appendChild(btn)
        }
        addCSSStyles()
        document.body?.appendChild(container)
    }
    fun Element.deactivate(){
        this.removeClass("active")
        this.addClass("inactive")
    }

    fun Element.activate(){
        this.removeClass("inactive")
        this.addClass("active")
    }
    fun addCSSStyles(){
        val styles = """
            .active {
                background-color: ${colorToCSSString(activeColor)};
            }
            .inactive {
                background-color: ${colorToCSSString(inactiveColor)};
            }
            .btn {
                transition: 0.2s;
            }
            .btn:hover {
                background-color: ${colorToCSSString(activeColor)};
            }
            
            """

        val styleSheet = document.createElement("style")
        styleSheet.setAttribute("type","text/css")
        styleSheet.innerHTML += styles
        document.head?.appendChild(styleSheet)
    }
    fun colorToCSSString(c: Color): String = "rgba(${c.r*255},${c.g*255},${c.b*255},${c.a})"

    fun calcLeftPadding(): Double{
        return (window.innerWidth - radioWidth) / 2.0
    }

    fun calcTopPadding(): Double {
        return window.innerHeight * 0.95
    }
}