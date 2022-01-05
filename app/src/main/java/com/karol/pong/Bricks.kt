package com.karol.pong


import android.content.res.Resources
import android.graphics.*
import androidx.core.graphics.scale
import java.security.AccessController.getContext


class Bricks(posX: Float, posY: Float, paintedBrick: Bitmap) {

    val paintedBrick1 = paintedBrick
    var paint = Paint()

//    var res: Resources = Resources.getSystem()
//    private var paintedBrick: Bitmap = BitmapFactory.decodeResource(res, R.drawable.paddle_kiwii).scale(getScreenWidth()/14, 50, true)

    var width = 10f
    var height = 50f

    var destroy = false

    private var bricks: RectF = RectF(posX, posY, posX + getScreenWidth()/14, posY + height)

    //private var paintedBrick: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.paddle_kiwii).scale(getScreenWidth()/14, 50, true)



    fun draw(canvas: Canvas?){

        paint.color = Color.BLACK

        //canvas?.drawRect(bricks, paint)
        canvas?.drawBitmap(paintedBrick1, bricks.left, bricks.top, null)

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