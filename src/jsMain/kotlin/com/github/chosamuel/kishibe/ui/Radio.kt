package com.github.chosamuel.kishibe.ui


import com.github.chosamuel.kishibe.svgns
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.svg.SVGElement

class Radio(
    val numBtns:Int = 3,
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
            btn.setAttribute("width", "$btnWidth")
            btn.setAttribute("id","$id")
            val defaultState = if(i==0) "active" else "inactive"
            btn.classList.add(defaultState)
            btn.classList.add("btn")
            btn.setAttribute("height", "$btnHeight")
            btn.setAttribute("x", "$btnx")
            btn.setAttribute("y", "$topPadding")
            btn.addEventListener("mousedown", {
                //find current active button and deactivate
                val activeBtn = document.querySelector(".active")
                activeBtn?.classList?.remove("active")
                activeBtn?.classList?.add("inactive")
                //activate current button
                btn.classList.remove("inactive")
                btn.classList.add("active")
                mode = i
                onClick()
            })
            UI.appendChild(btn)
        }
        addCSSStyles()
    }
    fun appendToDocument(){
        document.body?.appendChild(UI)
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