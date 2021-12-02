package com.karol.pong


import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint



class Paddle(context: Context) {


    var posX = 0f
    var posY = 0f
    var paint = Paint()
    var size = 50f


    fun update(){

        //posX

    }

    fun draw(canvas: Canvas?){

        //TODO Create square for the paddle

        canvas?.drawCircle(posX,posY,size,paint)

    }


}