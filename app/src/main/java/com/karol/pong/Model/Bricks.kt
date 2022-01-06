package com.karol.pong.Model


import android.content.res.Resources
import android.graphics.*
import androidx.core.graphics.minus
import kotlin.math.abs


class Bricks(posX: Float, posY: Float, paintedBrick: Bitmap, bScore: Int){

    val paintedBrick1 = paintedBrick
    var paint = Paint()

//    var res: Resources = Resources.getSystem()
//    private var paintedBrick: Bitmap = BitmapFactory.decodeResource(res, R.drawable.paddle_kiwii).scale(getScreenWidth()/14, 50, true)

    var width = 10f
    var height = 50f

    val brickScore = bScore
    var destroy = false

    private var bricks: RectF = RectF(posX, posY, posX + getScreenWidth()/14, posY + height)

    //private var paintedBrick: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.paddle_kiwii).scale(getScreenWidth()/14, 50, true)



    fun draw(canvas: Canvas?){

        paint.color = Color.BLACK

        //canvas?.drawRect(bricks, paint)
        canvas?.drawBitmap(paintedBrick1, bricks.left, bricks.top, null)

    }
    fun update(ball: Ball){

        if(RectF.intersects(bricks,ball.hitbox - abs(20f))){

            if(ball.speedY == 0f ) {
                ball.speedY = -1f
            }
            else {
                ball.speedY = abs(ball.speedY) * abs(-1)
            }
            destroy = true

            kotlin.io.println("Collision")

        }
    }


    private fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    private fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

}