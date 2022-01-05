package com.karol.pong


import android.content.res.Resources
import android.graphics.*
import androidx.core.graphics.scale
import java.security.AccessController.getContext


class Bricks(posX: Float, posY: Float) {

    var paint = Paint()

//    var res: Resources = Resources.getSystem()
//    private var paintedBrick: Bitmap = BitmapFactory.decodeResource(res, R.drawable.paddle_kiwii).scale(getScreenWidth()/14, 50, true)

    var width = 0f
    var height = 50f

    var destroy = false

    private var bricks: RectF = RectF(posX, posY, posX + getScreenWidth()/14, posY + height)



    fun draw(canvas: Canvas?){

        paint.color = Color.BLACK

        canvas?.drawRect(bricks, paint)
        //canvas?.drawBitmap(paintedBrick, bricks.top, bricks.left, null)

    }
    fun update(ball: Ball){

        if(RectF.intersects(ball.hitbox, bricks)){

            ball.speedY *= -1f

            destroy = true

            Setting.score++

            println("Collision")

        }
    }

    private fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    private fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

}