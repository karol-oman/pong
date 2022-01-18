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

/**
 * IntersectHandler handles all the intersects between the ball, paddle and bounds
 */
class IntersectHandler(context: Context) {

    private var hasScore = true

    private var playActivity = context as PlayActivity

    var rememberedBallPos = 0f

    fun intersects(ball: Ball, paddle: Paddle, bounds: Rect) {
        update(ball)

        /**
         * The ball has a flag to check if it has been given score or not.
         * If false, and the ball is greater than the paddle height + 100f, as well as the ball speed is positive (facing up), a score is given
         * Sets the flag "hasScore" to true and proceeds
         */

        if (!hasScore && ball.posY >= bounds.bottom.toFloat() - Setting.screenHeight / 6f - 100f && ball.speedY < 0 && Setting.gameMode == 0) {

            score ++
            hasScore = true

            checkIfLevelUp(ball)
        }

        /**
         * When the ball posY is above half of the screenHeight the "hasScore" flag is set to false
         * if the game mode is breakout proceeds with the brickIntersects()
         */

        if (ball.posY < bounds.bottom.toFloat() - Setting.screenHeight / 2) {
            hasScore = false
        }

        if (Setting.gameMode == 1) {
            brickIntersect(ball)
        }

        checkPaddleZone(paddle, ball)
    }

    /**
     * Checks if the score has reached the requirements for the next level
     * When next level occurs the speed is adjusted + popup
     */

    private fun checkIfLevelUp(ball: Ball){
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
                playActivity.updateLevelText("Level: 5")
            }
            51 -> playActivity.updateLevel(android.R.color.transparent)
        }
    }

    /**
     * Makes sure that the ball pos is within the GameView bounds
     */
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

    /**
     * Updates the ball position with the speed and the hitbox
     */

    private fun update(ball: Ball) {
        ball.posY += ball.speedY
        ball.posX += ball.speedX
        ball.hitbox = RectF(
            ball.posX - ball.size,
            ball.posY - ball.size,
            ball.posX + ball.size,
            ball.posY + ball.size)
    }


    private fun brickIntersect(ball: Ball){

        checkIntersect(ball)
        checkRemove()

    }

    /**
     * Checks if the ball intersects with a brick. If true, the ball bounces and sets the affected brick.destroy attribute to true
     * Remembers the ball position to prevent the function from running twice. Sets the ball.bounce flag to true which is then checked and set back in the next intersect-check
     */

    private fun checkIntersect(ball: Ball){
        for (brick in GameHandler.allBricks) {

            if (ball.posY != rememberedBallPos) {
                ball.bounce = false
            }

            if (!ball.bounce && RectF.intersects(brick.bricks, ball.hitbox)) {


                //SIDE HIT
                if (ball.posY < brick.bricks.bottom && ball.posY > brick.bricks.top) {
                    ball.speedX *= -1

                }
                //TOP/BOT HIT
                else {
                    ball.speedY *= -1
                    println(ball.speedX)
                }

                brick.destroy = true
                rememberedBallPos = ball.posY
                ball.bounce = true

            }
        }
    }

    /**
     * If a brick has the brick.destroy value as true, the brick is then removed from the list
     * The brick.score is then added to the score variable
     */
    private fun checkRemove(){
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

    /**
     * The paddle is divided into 6 zones, each zone has its own direction-behaviour for the ball
     */
    private fun checkPaddleZone(paddle: Paddle, ball: Ball){
        val widthPerZone = abs(paddle.width) / abs(6)
        if (RectF.intersects(paddle.paddle, ball.hitbox)) {
            println(ball.posY)
            val ballTotalSpeed = abs(ball.speedY) + abs(ball.speedX)

            when {
                //zon1
                ball.hitbox.centerX() < paddle.posX + widthPerZone -> {
                    ball.speedX = (ballTotalSpeed * 0.7f) * -1
                    ball.speedY = (ballTotalSpeed * 0.3f) * -1
                }
                //zon2
                ball.hitbox.centerX() < paddle.posX + widthPerZone * 2 -> {
                    ball.speedX = (ballTotalSpeed * 0.6f) * -1
                    ball.speedY = (ballTotalSpeed * 0.4f) * -1
                }
                //zon3
                ball.hitbox.centerX() < paddle.posX + widthPerZone * 3 -> {
                    ball.speedX = (ballTotalSpeed * 0.3f) * -1
                    ball.speedY = (ballTotalSpeed * 0.7f) * -1
                }
                //zon4
                ball.hitbox.centerX() < paddle.posX + widthPerZone * 4 -> {
                    ball.speedX = (ballTotalSpeed * 0.3f)
                    ball.speedY = (ballTotalSpeed * 0.7f) * -1
                }
                //zon5
                ball.hitbox.centerX() < paddle.posX + widthPerZone * 5 -> {
                    ball.speedX = (ballTotalSpeed * 0.6f)
                    ball.speedY = (ballTotalSpeed * 0.4f) * -1
                }
                //zon6
                ball.hitbox.centerX() <= paddle.posX + widthPerZone * 6 -> {
                    ball.speedX = (ballTotalSpeed * 0.7f)
                    ball.speedY = (ballTotalSpeed * 0.3f) * -1
                }
            }
        }
    }
}