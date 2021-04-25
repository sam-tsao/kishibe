package com.github.chosamuel.kishibe.shapes

import com.github.chosamuel.kishibe.Drawer
import com.github.chosamuel.kishibe.endPath
import com.github.chosamuel.kishibe.setStyle

fun Drawer.circle(x: Double, y: Double, r: Double){
    setStyle()
    ctx.beginPath()
    ctx.arc(x,y,r,0.0,360.0)
    endPath()
}