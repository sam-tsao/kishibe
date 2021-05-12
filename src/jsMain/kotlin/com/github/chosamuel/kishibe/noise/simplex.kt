package com.github.chosamuel.kishibe.noise

val KISHIBE_SIMPLEX = OpenSimplexNoise()

fun simplex(x: Double, y: Double, z: Double, w: Double): Double{
    return KISHIBE_SIMPLEX.random4D(x,y,z,w)
}