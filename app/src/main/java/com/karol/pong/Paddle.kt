package com.karol.pong

import android.R.attr
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.R.attr.y

import android.R.attr.x
import android.graphics.Rect


class Paddle(context: Context) {


    var posX = 0f
    var posY = 0f
    var paint = Paint()
    var size = 50f
    var speed = 5f


    fun update(){
        posY +=  posY

    }

    fun draw(canvas: Canvas?){


                                    //top = height
        //canvas?.drawRect (((size)+100), 80f, ((size)+ 500) .toFloat (), 70f, paint)

        canvas?.drawRect(0f,0f,200f, 50f, paint)








        //canvas?.drawRect(size,size,size,size, paint)

        /*canvas?.drawRect(
            posX, // left side of the rectangle to be drawn
            posY, // top side
            50f, // right side
            50f, // bottom side
            paint)*/


    }


}