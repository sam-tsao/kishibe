package com.github.chosamuel.kishibe.ui


import com.github.chosamuel.kishibe.svgns
import kotlinx.browser.document
import kotlinx.browser.window

class Radio(val numBtns:Int = 3) : BaseUI(){

    val btnWidth = 20
    val btnHeight = 20
    val paddingWidth = 30
    val UIWidth = numBtns * btnWidth + (numBtns-1) * paddingWidth
    val leftPadding = (window.innerWidth - UIWidth) * 0.5
    val topPadding = (window.innerHeight - (btnHeight*2.0))
    var mode = 0
    var onClick = {}

    init {
        for(i in 0 until numBtns){
            val btn = document.createElementNS(svgns, "rect")
            val btnx = leftPadding + (i * (btnWidth + paddingWidth))
            val id = "btn-${i+1}"
            btn.setAttribute("width", "$btnWidth")
            btn.setAttribute("id","$id")
            if(i == 0){
                btn.classList.add("active")
            } else {
                btn.classList.add("inactive")
            }
            btn.classList.add("btn")
            btn.setAttribute("height", "$btnHeight")
            btn.setAttribute("x", "$btnx")
            btn.setAttribute("y", "$topPadding")
            btn.addEventListener("mousedown", {
                val activeBtn = document.querySelector(".active")
                activeBtn?.classList?.remove("active")
                activeBtn?.classList?.add("inactive")
                btn.classList.remove("inactive")
                btn.classList.add("active")
                mode = i
                onClick()
            })
            UI.appendChild(btn)
        }
    }
    fun appendToDocument(){
        document.body?.appendChild(UI)
    }

    //optional
    fun setOnClick(func:()->Unit){
        onClick = func
    }

}