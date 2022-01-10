package com.karol.pong.Model


import android.graphics.*
import kotlin.math.abs


class Ball(var posX: Float, var posY: Float, var size: Float, var speedX: Float, var speedY: Float) {


    var paint = Paint()

    var width = 300f
    var height = 50f

    var hitbox: RectF = RectF(posX - size, posY - size, posX + size , posY + size)


    fun update(){
        posY += speedY
        posX += speedX
        hitbox = RectF(posX - size, posY - size, posX + size , posY + size)

    }


    fun draw (canvas: Canvas){

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