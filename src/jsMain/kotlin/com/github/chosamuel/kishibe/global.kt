package com.github.chosamuel.kishibe

import com.github.chosamuel.kishibe.math.Vec2
import com.github.chosamuel.kishibe.noise.OpenSimplexNoise
import kotlin.math.PI

//CONSTANTS
const val svgns = "http://www.w3.org/2000/svg"
const val TAU = PI * 2.0

//VARIABLES
var frameCount = 0
var mouseX: Double = 0.0
var mouseY: Double = 0.0
val KISHIBE_NOISE = OpenSimplexNoise() //global instance for wrapping

