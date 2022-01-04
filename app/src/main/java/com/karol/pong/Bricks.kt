package com.karol.pong


import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

class Bricks(posX: Float, posY: Float) {

    var paint = Paint()

    var width = 0f
    var height = 50f

    var destroy = false

    private var bricks: RectF = RectF(posX, posY, posX + getScreenWidth()/14, posY + height)

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

    private fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    private fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

}