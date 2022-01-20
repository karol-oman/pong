package com.karol.pong.controller

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.karol.pong.model.Ball
import com.karol.pong.model.Paddle
import com.karol.pong.model.Setting

/**
 * DrawHandler draws the game objects. The paddle and the ball gets a bitmap in GameView.
 * All the bricks are drawn here based on GameHandler.allBricks.
 *  @authors Sarah, Gustav, Karol, Calle
 */

class DrawHandler {

    fun draw(canvas : Canvas, ball : Ball, paddle : Paddle){

        val paint = Paint()
        paint.color = Color.TRANSPARENT

        canvas.drawCircle(ball.posX, ball.posY, ball.size, paint)
        canvas.drawRect(ball.hitbox, paint)

        paddle.paddle = RectF(paddle.posX, paddle.posY, paddle.posX + paddle.width, paddle.posY + paddle.height)
        canvas.drawRect(paddle.paddle, paint)

        if (Setting.gameMode == 1) {

            for (brick in GameHandler.allBricks) {

                canvas.drawBitmap(brick.paintedBrick, brick.bricks.left, brick.bricks.top, null)

            }
        }
    }
}