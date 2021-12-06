package com.karol.pong

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import androidx.core.graphics.red


class Ball(context: Context, var posX: Float, var posY: Float, var size: Float, var speedX: Float, var speedY: Float) {


    var paint = Paint()
    var paint2 = Paint()
    var width = 300f
    var height = 50f

    lateinit var hitbox: RectF

    fun update(){
        posY += speedY
        posX += speedX

    }


    fun draw (canvas: Canvas?){



        //canvas?.drawCircle(hitbox.centerX(),hitbox.centerY(),size,paint)


        hitbox = RectF(posX - size, posY - size, posX + size , posY + size)

        canvas?.drawRect(hitbox, paint2)

        canvas?.drawCircle(posX,posY,size,paint)

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
        if (posY + size > bounds.bottom - 50f){
            speedY *= -1

            println(bounds.bottom)
        }
    }


    private fun setup(){

    }
}