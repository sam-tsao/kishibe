package com.github.chosamuel.kishibe


fun Drawer.setStyle(){
    ctx.fillStyle = fillStyle
    ctx.strokeStyle = strokeStyle
    ctx.lineWidth = lineWidth
}

fun Drawer.endPath(){
    if(useFill) ctx.fill()
    if(useStroke) ctx.stroke()
}

//FILL

fun Drawer.fill(fill: String){
    useFill = true
    fillStyle = fill
}

fun Drawer.fill(r: Double, g: Double, b: Double, a: Double = 1.0){
    useFill = true
    fillStyle = "rgba($r,$g,$b,$a)"
}

fun Drawer.noFill(){
    useFill = false
}

//STROKE
fun Drawer.stroke(stroke: String){
    useStroke = true
    strokeStyle = stroke
}

fun Drawer.stroke(r: Double, g: Double, b: Double, a: Double= 1.0){
    useStroke = true
    strokeStyle = "rgba($r,$g,$b,$a)"
}


fun Drawer.noStroke(){
    useStroke = false
}
//STROKE WEIGHT
fun Drawer.strokeWeight(w: Double){
    lineWidth = w
}

//BACKGROUND
fun Drawer.clear(color: String){
    ctx.save()
    fill(color)
    setStyle()
    ctx.fillRect(0.0,0.0,width*1.0,height*1.0)
    ctx.restore()
}

fun Drawer.clear(r: Double, g: Double, b: Double, a: Double = 1.0){
    ctx.save()
    fill(r,g,b,a)
    setStyle()
    ctx.fillRect(0.0,0.0,width*1.0,height*1.0)
    ctx.restore()
}