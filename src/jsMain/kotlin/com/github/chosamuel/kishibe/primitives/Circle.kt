package com.github.chosamuel.kishibe.primitives


import com.github.chosamuel.kishibe.Drawer
import com.github.chosamuel.kishibe.endPath
import com.github.chosamuel.kishibe.math.Vec2
import com.github.chosamuel.kishibe.setStyle

data class Circle(val center: Vec2, val radius: Double) {
    constructor(x: Double, y: Double, radius: Double) : this(Vec2(x, y), radius)

    fun intersects(other: Circle): Boolean {
        val d = this.center.distanceTo(other.center)
        return d < (this.radius + other.radius)
    }

}

fun Drawer.circle(c: Circle){
    circle(c.center.x,c.center.y,c.radius)
}

fun Drawer.circle(center: Vec2, r: Double){
    circle(center.x,center.y,r)
}

fun Drawer.circle(x: Double, y: Double, r: Double){
    setStyle()
    ctx.beginPath()
    ctx.arc(x,y,r,0.0,360.0)
    endPath()
}