package com.karol.pong

import android.R.attr
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.R.attr.y

import android.R.attr.x
import android.graphics.Rect
import android.graphics.RectF


class Paddle(context: Context) {


    var posX = 0f
    var posY = 0f
    var paint = Paint()

    var width = 300f
    var height = 50f

    lateinit var paddle: RectF


    fun update(){
        posX +=  posX

    }

    fun draw(canvas: Canvas?){

        //canvas?.drawCircle(posX,posY,size,paint)

        paddle = RectF(posX, posY, posX + width, posY + height)

        canvas?.drawRect(paddle, paint)


    }


}