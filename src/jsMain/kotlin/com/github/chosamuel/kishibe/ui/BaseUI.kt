package com.github.chosamuel.kishibe.ui

import com.github.chosamuel.kishibe.svgns
import kotlinx.browser.document
import kotlinx.browser.window

open class BaseUI {
    val UI = document.createElementNS(svgns,"svg")
    var width = window.innerWidth
    var height = window.innerHeight

    init {
        UI.setAttribute("width","${width}")
        UI.setAttribute("height","${height}")
        UI.setAttribute("style","position: absolute;top:0;left:0;")
    }

    fun appendToDocument(){
        document.body?.appendChild(UI)
    }
}