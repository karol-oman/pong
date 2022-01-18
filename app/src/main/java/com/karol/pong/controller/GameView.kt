package com.karol.pong.controller


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.graphics.scale
import com.karol.pong.model.*
import com.karol.pong.R
import com.karol.pong.view.PlayActivity
import kotlin.math.abs


class GameView(context: Context?) : SurfaceView(context), SurfaceHolder.Callback, Runnable {

    private var thread: Thread? = null
    private lateinit var canvas: Canvas
    private lateinit var ball: Ball
    private var running = false

    private var totalBrickRowWidth = Setting.screenWidth / 12 + (Setting.screenWidth / 12 * 2) / 10
    private var brickHeight = 70

    private var level = 0

    private var playActivity = context as PlayActivity

    private lateinit var paddle: Paddle
    private var bounds = Rect()

    private var mHolder: SurfaceHolder? = holder

    private val randomBackground = (0..6).random()

    private val imgId = arrayOf(
        R.drawable.backgroundoneblur, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4,
        R.drawable.bg5, R.drawable.bg6, R.drawable.bg7
    )

    private val ballArray = arrayOf(
        R.drawable.balll1,
        R.drawable.ball2,
        R.drawable.ball3,
        R.drawable.ball4,
        R.drawable.shuri
    )

    private val paddleArray = arrayOf(R.drawable.bamboo, R.drawable.chopsticks, R.drawable.bowl)

    private val brickArray = arrayOf(
        R.drawable.paddle_dragon,
        R.drawable.paddle_green_apple,
        R.drawable.paddle_kiwii,
        R.drawable.paddle_purple_apple,
        R.drawable.paddle_strawberry,
        R.drawable.paddle_watermelon
    )

    private var hasStarted = false

    private var background: Bitmap =
        BitmapFactory.decodeResource(resources, imgId[randomBackground])
            .scale(Setting.screenWidth, Setting.screenHeight)
    private var paintedBall: Bitmap =
        BitmapFactory.decodeResource(resources, ballArray[Setting.ballID]).scale(110, 110, true)

    private var paintedPaddle: Bitmap =
        BitmapFactory.decodeResource(resources, paddleArray[Setting.paddleID])
            .scale(Setting.paddleWidth.toInt(), Setting.paddleHeight.toInt(), true)


    init {

        if (mHolder != null)
            mHolder?.addCallback(this)


        setup()
    }

    /**
     * IntersectHandler
     */

    private var intersectHandler = IntersectHandler(playActivity)

    /**
     * DrawHandler
     */

    private var drawHandler = DrawHandler()

    private fun generateBricks() {

        val dragonfruit: Bitmap = BitmapFactory.decodeResource(resources, brickArray[0])
            .scale(totalBrickRowWidth, brickHeight, true)
        val greenapple: Bitmap = BitmapFactory.decodeResource(resources, brickArray[1])
            .scale(totalBrickRowWidth, brickHeight, true)
        val kiwi: Bitmap = BitmapFactory.decodeResource(resources, brickArray[2])
            .scale(totalBrickRowWidth, brickHeight, true)
        val purpleapple: Bitmap = BitmapFactory.decodeResource(resources, brickArray[3])
            .scale(totalBrickRowWidth, brickHeight, true)
        val strawberry: Bitmap = BitmapFactory.decodeResource(resources, brickArray[4])
            .scale(totalBrickRowWidth, brickHeight, true)
        val watermelon: Bitmap = BitmapFactory.decodeResource(resources, brickArray[5])
            .scale(totalBrickRowWidth, brickHeight, true)




        GameHandler.paintArray.clear()

        /**
         * Bricks are generated based on the strings in GameHandler
         */


        when (level) {

            0 -> GameHandler.ball()
            1 -> {
                GameHandler.labyrinth()
                ball.speedY *= Setting.speedMultiplier
            }
            2 -> {
                GameHandler.japan()
                ball.speedY *= Setting.speedMultiplier
            }
            3 -> {
                GameHandler.karolLevel()
                ball.speedY *= Setting.speedMultiplier
            }

            4 -> {
                GameHandler.apple()
                ball.speedY *= Setting.speedMultiplier
            }
            5 -> {
                GameHandler.sarahLevel()
                ball.speedY *= Setting.speedMultiplier
            }
            6 -> {
                GameHandler.bill()
                ball.speedY *= Setting.speedMultiplier
            }
            else -> {
                GameHandler.sarahLevel()
                ball.speedY *= 2
            }


        }


        // Clears the GameHandler.allBricks from any remainders of previous games
        GameHandler.allBricks.clear()

        var xposBrick = 0f
        val yposBrick = 20f
        val margin = 10f

        for (i in 0..9) {

            var multiplyHeight = 1
            var multiplyMargin = 0

            for (string in GameHandler.paintArray) {

                //The height of a brick is 50f
                val total = (70f * multiplyHeight) + (margin * multiplyMargin) + yposBrick

                when (string[i]) {

                    'D' -> GameHandler.allBricks.add(
                        Bricks(
                            xposBrick,
                            total,
                            dragonfruit,
                            1,
                            Setting.brickHeight
                        )
                    )
                    'G' -> GameHandler.allBricks.add(
                        Bricks(
                            xposBrick,
                            total,
                            greenapple,
                            1,
                            Setting.brickHeight
                        )
                    )
                    'K' -> GameHandler.allBricks.add(
                        Bricks(
                            xposBrick,
                            total,
                            kiwi,
                            1,
                            Setting.brickHeight
                        )
                    )
                    'P' -> GameHandler.allBricks.add(
                        Bricks(
                            xposBrick,
                            total,
                            purpleapple,
                            1,
                            Setting.brickHeight
                        )
                    )
                    'S' -> GameHandler.allBricks.add(
                        Bricks(
                            xposBrick,
                            total,
                            strawberry,
                            1,
                            Setting.brickHeight
                        )
                    )
                    'W' -> GameHandler.allBricks.add(
                        Bricks(
                            xposBrick,
                            total,
                            watermelon,
                            1,
                            Setting.brickHeight
                        )
                    )

                }

                multiplyHeight++
                multiplyMargin++

            }

            xposBrick += Setting.brickWidth + Setting.margin

        }

    }


    private fun setup() {

        Setting.score = 0

        if (Setting.gameMode == 0) playActivity.updateLevelText("Level: 1")
        //Generates bricks in game
        else if (Setting.gameMode == 1) generateBricks()


        //Creates ball and paddle objects
        paddle = Paddle()
        paddle.posX = (Setting.screenWidth / 2f) - paddle.width / 2f
        ball = Ball(paddle.posX + paddle.width / 2, paddle.posY, 30f, 0f, 0f, 50f)

    }

    private fun start() {
        running = true
        thread = Thread(this)
        thread?.start()
    }

    private fun stop() {
        running = false
        try {
            thread?.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun update() {

        if (ball.posX > bounds.right) {
            ball.posX -= 10f
        } else if (ball.posX < bounds.left) {
            ball.posX += 10f
        }

        if (Setting.gameMode == 1) {

            if (GameHandler.allBricks.isEmpty() && ball.posY > (Setting.screenHeight / 2).toFloat()) {
                level++
                generateBricks()
            }

        }

    }

    private fun draw() {

        canvas = mHolder!!.lockCanvas()

        //Drawing background to canvas
        canvas.drawBitmap(background, matrix, null)

        drawHandler.draw(canvas, ball, paddle)


        //
        canvas.drawBitmap(paintedBall, ball.hitbox.left - 23, ball.hitbox.top - 23, null)
        canvas.drawBitmap(paintedPaddle, paddle.paddle.left, paddle.paddle.top, null)




        if (Setting.gameMode == 0) {
            playActivity.updateScore("Total score: ${Setting.score}")
        }
        if (Setting.gameMode == 1) {
            playActivity.updateScore("Total score: ${Setting.score}")
        }

        mHolder!!.unlockCanvasAndPost(canvas)
    }


    override fun surfaceCreated(p0: SurfaceHolder) {

    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

        bounds = Rect(0, 0, p2, p3)

        paddle.posY = Setting.screenHeight - Setting.screenHeight / 6f
        ball.posY = Setting.screenHeight - Setting.screenHeight / 6f - 50f

        start()
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        stop()
    }

    override fun run() {
        while (running && !Setting.rageQuit) {
            draw()
            intersectHandler.intersects(ball, paddle, bounds)
            update()
            intersectHandler.checkBounds(ball, bounds)
            checkIfDead()


        }
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        paddle.posX = event!!.x - paddle.width / 2

        //Sets the position of paddle & ball to right of screen if paddle goes "outside" screen
        if (paddle.posX + paddle.width > bounds.right) {
            paddle.posX = bounds.right.toFloat() - paddle.width
        }
        if (paddle.posX < bounds.left) {
            paddle.posX = bounds.left.toFloat()
        }

        if (!hasStarted) {
            ball.posX = event.x

            if (event.action == MotionEvent.ACTION_UP) {

                hasStarted = true
                ball.posX = abs(paddle.posX) + abs(paddle.width) / abs(2)
                ball.speedX = Setting.ballSpeedX
                ball.speedY = Setting.ballSpeedY
                return true
            }

            if (ball.posX < paddle.posX + paddle.width / 2 || ball.posX > paddle.posX + paddle.width / 2) {

                ball.posX = abs(paddle.posX) + abs(paddle.width) / abs(2)

            }

            if (paddle.posX + paddle.width > bounds.right) {
                ball.posX = abs(paddle.posX) + abs(paddle.width) / abs(2)
            }
        }

        return true

    }

    private fun checkIfDead() {
        if (ball.posY + ball.size > bounds.bottom) {
            running = false
            playActivity.showGameOver(Setting.gameMode)
            playActivity.updateLevel(android.R.color.transparent)
            Setting.score = Setting.score

        }
    }
}