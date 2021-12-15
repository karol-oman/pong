package com.karol.pong

import android.content.Context
import android.graphics.*


class Ball(context: Context, var posX: Float, var posY: Float, var size: Float, var speedX: Float, var speedY: Float) {


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


    }

    fun checkBounds(bounds: Rect){
        if(posX - size < 0){
            speedX *= -1

        }
        if(posX + size > bounds.right){
            speedX *= -1

        }
        if(posY - size < 0){
            speedY *= -1

        }
        //TODO I MOVED THIS TO GAME VIEW TO BE ABLE TO STOP GAME LOOP
        if (posY + size > bounds.bottom){
            //speedY *= -1

            println("u suck")

        }
    }

}