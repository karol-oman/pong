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

    lateinit var zone1: RectF
    lateinit var zone2: RectF
    lateinit var zone3: RectF
    lateinit var zone4: RectF
    lateinit var zone5: RectF
    lateinit var zone6: RectF
    lateinit var zone7: RectF

    var totZones = 7
    var widthZone = Math.abs(width) / Math.abs(totZones)



    fun draw(canvas: Canvas?){

        zone1 = RectF(posX , posY, posX + Math.abs(widthZone), posY + height)

        zone2 = RectF(posX + Math.abs(widthZone), posY, posX + Math.abs(widthZone*2), posY + height)

        zone3 = RectF(posX + Math.abs(widthZone*2), posY, posX + Math.abs(widthZone*3), posY + height)

        zone4 = RectF(posX + Math.abs(widthZone*3), posY, posX + Math.abs(widthZone*4), posY + height)

        zone5 = RectF(posX + Math.abs(widthZone*4), posY, posX + Math.abs(widthZone*5), posY + height)

        zone6 = RectF(posX + Math.abs(widthZone*5), posY, posX + Math.abs(widthZone*6), posY + height)

        zone7 = RectF(posX + Math.abs(widthZone*6), posY, posX + Math.abs(widthZone*7), posY + height)


        canvas?.drawRect(zone1, paint1)
        canvas?.drawRect(zone2, paint2)
        canvas?.drawRect(zone3, paint3)
        canvas?.drawRect(zone4, paint4)
        canvas?.drawRect(zone5, paint5)
        canvas?.drawRect(zone6, paint6)
        canvas?.drawRect(zone7, paint7)

        println()

    }


}