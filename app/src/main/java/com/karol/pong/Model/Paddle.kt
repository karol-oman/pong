package com.karol.pong.Model



import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import androidx.core.graphics.minus
import kotlin.math.abs


class Paddle(

    var posX: Float = 0f,
    var posY: Float = 0f,
    var paint: Paint = Paint(),
    var width: Float = Setting.paddleWidth,
    var height: Float = Setting.paddleHeight,

)

{

    lateinit var paddle: RectF


    fun draw(canvas: Canvas?){

        paint.color = Color.TRANSPARENT

        paddle = RectF(posX, posY, posX + width, posY + height)
        canvas?.drawRect(paddle, paint)

    }
}