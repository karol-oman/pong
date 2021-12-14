package com.karol.pong

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class Bricks(var posX: Float, var posY: Float) {

    var paint = Paint()

    var width = 200f
    var height = 100f

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