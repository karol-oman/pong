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

    //For testing purposes
    var paint1 = Paint()
    var paint2 = Paint()
    var paint3 = Paint()
    var paint4 = Paint()
    var paint5 = Paint()
    var paint6 = Paint()
    var paint7 = Paint()


    var width = 500f
    var height = 50f

      lateinit var paddle: RectF

    private var totZones = 7
    private var widthZone = abs(width) / abs(totZones)

    fun draw(canvas: Canvas?){

        paint1.color = Color.TRANSPARENT

        paddle = RectF(posX, posY, posX + width, posY + height)
        canvas?.drawRect(paddle, paint1)

    }
}