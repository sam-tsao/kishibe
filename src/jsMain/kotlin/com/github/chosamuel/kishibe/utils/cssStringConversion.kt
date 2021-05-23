package com.github.chosamuel.kishibe.utils

import com.github.chosamuel.kishibe.application.renderer.Color

fun colorToCSSString(c: Color): String = "rgba(${c.r*255},${c.g*255},${c.b*255},${c.a})"
