package com.github.chosamuel.kishibe.ui

import com.github.chosamuel.kishibe.svgns
import kotlinx.browser.document
import kotlinx.browser.window

open class BaseUI {
    val UI = document.createElementNS(svgns,"svg")

    init {
        UI.setAttribute("width","${window.innerWidth}")
        UI.setAttribute("height","${window.innerHeight}")
        UI.setAttribute("style","position: absolute;top:0;left:0;")
    }
}