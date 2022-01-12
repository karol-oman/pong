package com.karol.pong.Model


import android.graphics.*
import androidx.core.graphics.minus
import kotlin.math.abs


class Bricks(

    var posX: Float,
    var posY: Float,
    private val paintedBrick: Bitmap,
    var brickScore: Int,
    val height : Float,
    var destroy: Boolean = false,

    private var bricks: RectF = RectF(
        posX,
        posY,
        posX + Setting.brickWidth,
        posY + Setting.brickHeight
    )
)

{

    fun draw(canvas: Canvas?){
        var paint = Paint()
        paint.color = Color.BLACK

        //canvas?.drawRect(bricks, paint)
        canvas?.drawBitmap(paintedBrick, bricks.left, bricks.top, null)

    }
    fun update(ball: Ball){

        if (ball.posY != ball.rememberedBallPos){
            ball.bounce = false
        }

        if(!ball.bounce && RectF.intersects(bricks,ball.hitbox - abs(20f))){


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
            ball.rememberedBallPos = ball.posY
            ball.bounce = true

        }
    }
}