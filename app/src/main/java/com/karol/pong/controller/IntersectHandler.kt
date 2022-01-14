package com.karol.pong.controller

import android.content.Context
import android.graphics.Rect
import android.graphics.RectF
import com.karol.pong.model.Ball
import com.karol.pong.model.Paddle
import com.karol.pong.model.Setting
import com.karol.pong.model.Setting.score
import com.karol.pong.R
import com.karol.pong.view.PlayActivity
import kotlin.math.abs

class IntersectHandler(context: Context) {

    private var hasScore = true

    private var playActivity = context as PlayActivity

    var rememberedBallPos = 0f

    fun intersects(ball: Ball, paddle: Paddle, bounds: Rect) {
        update(ball)

        val widthPerZone = abs(paddle.width) / abs(6)
        if (!hasScore && ball.posY >= bounds.bottom.toFloat() - Setting.screenHeight / 6f - 100f && ball.speedY < 0 && Setting.gameMode == 0) {

            score++
            hasScore = true

            when (score) {

                0 -> {
                    playActivity.updateLevel(android.R.color.transparent)
                    playActivity.updateLevelText("Level: 1")
                }
                10 -> {
                    ball.speedY *= 1.5f
                    playActivity.updateLevel(R.drawable.levelup)
                    playActivity.updateLevelText("Level: 2")
                }
                11 -> {
                    playActivity.updateLevel(android.R.color.transparent)

                }
                20 -> {
                    ball.speedY *= 1.5f
                    playActivity.updateLevel(R.drawable.levelup)
                    playActivity.updateLevelText("Level: 3")
                }
                21 -> playActivity.updateLevel(android.R.color.transparent)

                30 -> {
                    ball.speedY *= 1.5f
                    playActivity.updateLevel(R.drawable.levelup)
                    playActivity.updateLevelText("Level: 4")
                }
                31 -> playActivity.updateLevel(android.R.color.transparent)

                50 -> {
                    ball.speedY *= 1.5f
                    playActivity.updateLevel(R.drawable.levelup)
                    playActivity.updateLevelText("Level: 4")
                }
                51 -> playActivity.updateLevel(android.R.color.transparent)
            }
        }

        if (Setting.gameMode == 1) {

            for (brick in GameHandler.allBricks) {

                if (ball.posY != rememberedBallPos) {
                    ball.bounce = false
                }

                if (!ball.bounce && RectF.intersects(brick.bricks, ball.hitbox)) {


                    if (ball.posY < brick.bricks.bottom && ball.posY > brick.bricks.top) {
                        ball.speedX *= -1
                        println("SIDE HIT")
                    } else {
                        ball.speedY *= -1
                        println(ball.speedX)
                        println("TOP/BOT HIT")
                        println("2")
                    }

                    brick.destroy = true
                    rememberedBallPos = ball.posY
                    ball.bounce = true

                }
            }
            val x = GameHandler.allBricks.toMutableList()

            for (brick in x) {
                if (brick.destroy) {
                    println("destroyed")
                    if (GameHandler.allBricks.contains(brick)) {
                        score += brick.brickScore
                        GameHandler.allBricks.remove(brick)
                    }

                }
            }
        }

        if (ball.posY < bounds.bottom.toFloat() - Setting.screenHeight / 2) {
            hasScore = false
        }

        if (RectF.intersects(paddle.paddle, ball.hitbox)) {
            println(ball.posY)
            val ballTotalSpeed = abs(ball.speedY) + abs(ball.speedX)

            when {
                ball.hitbox.centerX() < paddle.posX + widthPerZone -> {
                    println("zon1")
                    ball.speedX = (ballTotalSpeed * 0.7f) * -1
                    ball.speedY = (ballTotalSpeed * 0.3f) * -1
                }
                ball.hitbox.centerX() < paddle.posX + widthPerZone * 2 -> {
                    println("zon2")
                    ball.speedX = (ballTotalSpeed * 0.6f) * -1
                    ball.speedY = (ballTotalSpeed * 0.4f) * -1
                }
                ball.hitbox.centerX() < paddle.posX + widthPerZone * 3 -> {
                    println("zon3")
                    ball.speedX = (ballTotalSpeed * 0.3f) * -1
                    ball.speedY = (ballTotalSpeed * 0.7f) * -1
                }
                ball.hitbox.centerX() < paddle.posX + widthPerZone * 4 -> {
                    println("zon4")
                    ball.speedX = (ballTotalSpeed * 0.3f)
                    ball.speedY = (ballTotalSpeed * 0.7f) * -1
                }
                ball.hitbox.centerX() < paddle.posX + widthPerZone * 5 -> {
                    println("zon5")
                    ball.speedX = (ballTotalSpeed * 0.6f)
                    ball.speedY = (ballTotalSpeed * 0.4f) * -1
                }
                ball.hitbox.centerX() <= paddle.posX + widthPerZone * 6 -> {
                    println("zon6")
                    ball.speedX = (ballTotalSpeed * 0.7f)
                    ball.speedY = (ballTotalSpeed * 0.3f) * -1
                }
            }
        }
    }

    fun checkBounds(ball: Ball, bounds: Rect) {

        if (ball.posX - ball.size < 0) {
            ball.speedX *= -1
        }

        if (ball.posX + ball.size > bounds.right) {
            ball.speedX *= -1
        }

        if (abs(ball.posY) - abs(ball.size) - abs(100) < bounds.top) {
            ball.speedY *= -1
        }
    }

    private fun update(ball: Ball) {
        ball.posY += ball.speedY
        ball.posX += ball.speedX
        ball.hitbox = RectF(
            ball.posX - ball.size,
            ball.posY - ball.size,
            ball.posX + ball.size,
            ball.posY + ball.size)
    }
}