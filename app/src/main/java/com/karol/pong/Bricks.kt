package com.karol.pong


import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

class Bricks(posX: Float, posY: Float) {

    var paint = Paint()

    var width = 200f
    var height = 100f

    var destroy = false

    var bricks: RectF = RectF(posX, posY, posX + width, posY + height)

    fun draw(canvas: Canvas?){

        canvas?.drawRect(bricks, paint)

    }
    fun update(ball: Ball){

        if(RectF.intersects(ball.hitbox, bricks)){

            ball.speedY *= -1f

            destroy = true

            Setting.score++

            println("Collision")





        }
    }

}