package com.karol.pong.Model


import android.graphics.*
import kotlin.math.abs


class Ball(

    var posX: Float,
    var posY: Float,
    var size: Float,
    var speedX: Float,
    var speedY: Float,
    var height : Float,
    var bounce: Boolean = false,
    var hitbox: RectF = RectF(

        posX - size,
        posY - size,
        posX + size,
        posY + size

    )
)

{

    var rememberedBallPos = 0f

    fun update(){
        posY += speedY
        posX += speedX
        hitbox = RectF(posX - size, posY - size, posX + size , posY + size)
    }

    fun draw (canvas: Canvas){

        var paint = Paint()
        paint.color = Color.TRANSPARENT
        canvas.drawCircle(posX,posY,size,paint)
        canvas.drawRect(hitbox, paint)
    }

    fun checkBounds(bounds: Rect){

        if(posX - size < 0){
            speedX *= -1
        }

        if(posX + size > bounds.right){
            speedX *= -1
        }

        if(abs(posY) - abs(size) - abs(100) < bounds.top){
            speedY *= -1
        }
    }
}