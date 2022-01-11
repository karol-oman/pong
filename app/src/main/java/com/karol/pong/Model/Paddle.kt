package com.karol.pong.Model



import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import androidx.core.graphics.minus
import kotlin.math.abs


class Paddle {


    var posX = 0f
    var posY = 0f
    var paint = Paint()
    var width = Setting.paddleWidth
    var height = Setting.paddleHeight
    lateinit var paddle: RectF

    fun draw(canvas: Canvas?){

        paint.color = Color.TRANSPARENT

        paddle = RectF(posX, posY, posX + width, posY + height)
        canvas?.drawRect(paddle, paint)

    }
}