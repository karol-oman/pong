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

        canvas?.drawCircle(posX,posY,size,paint)

    }


}