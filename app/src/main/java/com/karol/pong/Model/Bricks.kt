package com.karol.pong.Model


import android.content.res.Resources
import android.graphics.*
import androidx.core.graphics.minus
import kotlin.math.abs


class Bricks(posX: Float, posY: Float, private val paintedBrick: Bitmap, bScore: Int, val height : Float){

    var paint = Paint()
    val brickScore = bScore
    var destroy = false

    private var bricks: RectF = RectF(posX, posY, posX + Setting.brickWidth, posY + Setting.brickHeight)


    fun draw(canvas: Canvas?){

        paint.color = Color.BLACK

        //canvas?.drawRect(bricks, paint)
        canvas?.drawBitmap(paintedBrick, bricks.left, bricks.top, null)

    }
    fun update(ball: Ball){

        if(RectF.intersects(bricks,ball.hitbox - abs(20f))){

            if(ball.posY < bricks.bottom && ball.posY  > bricks.top)
            {
                ball.speedX *= -1
                println("SIDE HIT")
            }
            else {
                ball.speedY *= -1
                println(ball.speedX)
                println("TOP/BOT HIT")
                println("2")
            }


            destroy = true

            kotlin.io.println("Collision")

        }
    }

}