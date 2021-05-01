package com.github.chosamuel.kishibe.noise


import com.github.chosamuel.kishibe.KISHIBE_NOISE

fun simplex(x: Double, y: Double):Double {
    return KISHIBE_NOISE.random2D(x,y)
}