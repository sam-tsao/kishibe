package com.github.chosamuel.kishibe.webgl


import com.github.chosamuel.kishibe.math.Vector2
import com.github.chosamuel.kishibe.math.Vector3
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
fun Float32Array(listOfVertices: List<Vector3>): Float32Array{
    val arr = Float32Array(listOfVertices.size*3)
    listOfVertices.forEachIndexed {i,v->
        arr[i*3] = v.x.toFloat()
        arr[i*3+1] = v.y.toFloat()
        arr[i*3+2] = v.z.toFloat()
    }
    return arr
}