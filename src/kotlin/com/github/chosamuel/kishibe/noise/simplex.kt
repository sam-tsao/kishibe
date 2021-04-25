package com.github.chosamuel.kishibe.noise

import com.github.chosamuel.kishibe.Drawer

fun Drawer.simplex(x: Double, y: Double):Double {
    return n.random2D(x,y)
}