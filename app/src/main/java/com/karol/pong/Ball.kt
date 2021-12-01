package com.karol.pong

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint


class Ball(context: Context) {

    var posX  = 0f
    var posY = 0f
    var paint = Paint()
    var size = 50f
    var speed = 5f

    //var ballImg: Bitmap = BitmapFactory.decodeResource()

    //var ball1: Bitmap = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_baseline_sports_baseball_24)



    fun update(){
        posY +=  speed

    }
    fun draw (canvas: Canvas?){

        canvas?.drawCircle(posX,posY,size,paint)
    }

    private fun setup(){

    }
}