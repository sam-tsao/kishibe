package com.github.chosamuel.kishibe


fun Drawer.setStyle(){
    ctx.fillStyle = fillStyle
    ctx.strokeStyle = strokeStyle
}

fun Drawer.endPath(){
    if(useFill) ctx.fill()
    if(useStroke) ctx.stroke()
}

fun Drawer.setFill(fill: String){
    useFill = true
    fillStyle = fill
}

fun Drawer.setFill(r: Double, g: Double, b: Double){
    useFill = true
    fillStyle = "rgb(${r},${g},${b})"
}

fun Drawer.setFill(r: Int, g: Int, b: Int){
    useFill = true
    fillStyle = "rgb(${r},${g},${b})"
}

fun Drawer.setStroke(stroke: String){
    useStroke = true
    strokeStyle = stroke
}

fun Drawer.setStroke(r: Double, g: Double, b: Double){
    useStroke = true
    strokeStyle = "rgb(${r*255.0},${g*255.0},${b*255.0})"
}

fun Drawer.setStroke(r: Int, g: Int, b: Int){
    useStroke = true
    strokeStyle = "rgb(${r},${g},${b})"
}

fun Drawer.noStroke(){
    useStroke = false
}

fun Drawer.noFill(){
    useFill = false
}