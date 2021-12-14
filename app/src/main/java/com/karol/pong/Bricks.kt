package com.karol.pong

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class Bricks(context: Context) {

    var posX = 200f
    var posY = 200f
    var paint = Paint()

    var width = 300f
    var height = 50f

    lateinit var bricksList: ArrayList<RectF>

    lateinit var bricks: RectF


    fun draw(canvas: Canvas?){

        bricks = RectF(posX, posY, posX + width, posY + height)

        canvas?.drawRect(bricks, paint)


    }
    fun destroyBrick(canvas: Canvas?){

        paint.color = Color.TRANSPARENT

        canvas?.drawRect(bricks, paint)

    }
}