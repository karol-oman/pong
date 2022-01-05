package com.karol.pong



import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
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

    //Paddle zones
//    lateinit var zone1: RectF
//    lateinit var zone2: RectF
//    lateinit var zone3: RectF
//    lateinit var zone4: RectF
//    lateinit var zone5: RectF
//    lateinit var zone6: RectF
//    lateinit var zone7: RectF

      lateinit var paddle: RectF

    private var totZones = 7
    private var widthZone = abs(width) / abs(totZones)

    fun draw(canvas: Canvas?){

//        zone1 = RectF(posX , posY, posX + abs(widthZone), posY + height)
//
//        zone2 = RectF(posX + abs(widthZone), posY, posX + abs(widthZone*2), posY + height)
//
//        zone3 = RectF(posX + abs(widthZone*2), posY, posX +abs(widthZone*3), posY + height)
//
//        zone4 = RectF(posX + abs(widthZone*3), posY, posX + abs(widthZone*4), posY + height)
//
//        zone5 = RectF(posX + abs(widthZone*4), posY, posX + abs(widthZone*5), posY + height)
//
//        zone6 = RectF(posX + abs(widthZone*5), posY, posX + abs(widthZone*6), posY + height)
//
//        zone7 = RectF(posX + abs(widthZone*6), posY, posX + abs(widthZone*7), posY + height)

//        canvas?.drawRect(zone1, paint1)
//        canvas?.drawRect(zone2, paint2)
//        canvas?.drawRect(zone3, paint3)
//        canvas?.drawRect(zone4, paint4)
//        canvas?.drawRect(zone5, paint5)
//        canvas?.drawRect(zone6, paint6)
//        canvas?.drawRect(zone7, paint7)
        paint1.color = Color.TRANSPARENT

        paddle = RectF(posX, posY, posX + width, posY + height)
        canvas?.drawRect(paddle, paint1)

    }


}