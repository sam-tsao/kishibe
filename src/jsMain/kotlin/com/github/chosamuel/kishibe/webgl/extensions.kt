package com.github.chosamuel.kishibe.webgl


import com.github.chosamuel.kishibe.math.Vector2
import org.khronos.webgl.Float32Array
import org.khronos.webgl.set


fun Float32Array(listOfVertices: List<Vector2>): Float32Array{
    val arr = Float32Array(listOfVertices.size*2)
    listOfVertices.forEachIndexed {i,v->
        arr[i*2] = v.x.toFloat()
        arr[i*2+1] = v.y.toFloat()
    }
    return arr
}