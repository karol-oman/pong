package com.karol.pong.Controller


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.graphics.scale
import com.karol.pong.Model.*
import com.karol.pong.R
import com.karol.pong.View.PlayActivity
import kotlin.math.abs


class GameView(context: Context?) : SurfaceView(context), SurfaceHolder.Callback, Runnable {

    private var thread: Thread? = null
    private var running = false
    private lateinit var canvas: Canvas
    private lateinit var ball: Ball

    var totalBrickRowWidth = Setting.screenWidth / 12 + (Setting.screenWidth / 12 * 2) / 10
    var brickHeight = 70

    private var score: Int = 0
    private var level = 0

    private var playActivity = context as PlayActivity

    private lateinit var paddle: Paddle
    private var bounds = Rect()

    private var mHolder: SurfaceHolder? = holder

    private val randomBackground = (0..6).random()

    private var hasScore = true

    //private val randomBallYSpeed = (-30..30).random().toFloat()
    //private val randomBallXSpeed = (-30..30).random().toFloat()

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
        BitmapFactory.decodeResource(resources, paddleArray[Setting.paddleID]).scale(Setting.paddleWidth.toInt(), Setting.paddleHeight.toInt(), true)


    init {

        if (mHolder != null)
            mHolder?.addCallback(this)

        println("running $running")

        setup()
    }

    /**
     * Controllers + objects are setup here
     */

    var paddleC = PaddleController(
        Paddle()
    )


    private fun generateBricks() {

        var dragonfruit: Bitmap = BitmapFactory.decodeResource(resources, brickArray[0])
            .scale(totalBrickRowWidth, brickHeight, true)
        var greenapple: Bitmap = BitmapFactory.decodeResource(resources, brickArray[1])
            .scale(totalBrickRowWidth, brickHeight, true)
        var kiwi: Bitmap = BitmapFactory.decodeResource(resources, brickArray[2])
            .scale(totalBrickRowWidth, brickHeight, true)
        var purpleapple: Bitmap = BitmapFactory.decodeResource(resources, brickArray[3])
            .scale(totalBrickRowWidth, brickHeight, true)
        var strawberry: Bitmap = BitmapFactory.decodeResource(resources, brickArray[4])
            .scale(totalBrickRowWidth, brickHeight, true)
        var watermelon: Bitmap = BitmapFactory.decodeResource(resources, brickArray[5])
            .scale(totalBrickRowWidth, brickHeight, true)




        GameHandler.paintArray.clear()

        /**
         * Bricks are generated based on the strings in GameHandler
         */



        when (level) {

            0 -> GameHandler.labyrinth()
            1 -> {
                GameHandler.labyrinth()
                ball.speedY *= Setting.speedMultiplier
            }
            2 -> {
                GameHandler.ball()
                ball.speedY *= Setting.speedMultiplier
            }
            3 -> {
                GameHandler.japan()
                ball.speedY *= Setting.speedMultiplier
            }

            4 -> {
                GameHandler.karolLevel()
                ball.speedY *= Setting.speedMultiplier
            }
            5 -> {
                GameHandler.apple()
                ball.speedY *= Setting.speedMultiplier
            }
            6 -> {
                GameHandler.sarahLevel()
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
                var total = (70f * multiplyHeight) + (margin * multiplyMargin) + yposBrick

                when (string[i]) {

                    'D' -> GameHandler.allBricks.add(Bricks(xposBrick, total, dragonfruit, 1, Setting.brickHeight))
                    'G' -> GameHandler.allBricks.add(Bricks(xposBrick, total, greenapple, 1, Setting.brickHeight))
                    'K' -> GameHandler.allBricks.add(Bricks(xposBrick, total, kiwi, 1, Setting.brickHeight))
                    'P' -> GameHandler.allBricks.add(Bricks(xposBrick, total, purpleapple, 1, Setting.brickHeight))
                    'S' -> GameHandler.allBricks.add(Bricks(xposBrick, total, strawberry, 1, Setting.brickHeight))
                    'W' -> GameHandler.allBricks.add(Bricks(xposBrick, total, watermelon, 1, Setting.brickHeight))

                }

                multiplyHeight++
                multiplyMargin++

            }

            xposBrick += Setting.brickWidth + Setting.margin

        }

    }


    private fun setup() {

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
        }
        if (ball.posX < bounds.left) {
            ball.posX += 10f
        }

        ball.update()

        if (Setting.gameMode == 1) {

            for (brick in GameHandler.allBricks) {
                brick.update(ball)
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

            paddle.draw(canvas)

            ball.draw(canvas)


            //
            canvas.drawBitmap(paintedBall, ball.hitbox.left - 23, ball.hitbox.top - 23, null)
            canvas.drawBitmap(paintedPaddle, paddle.paddle.left, paddle.paddle.top, null)

            if (Setting.gameMode == 1) {

                for (brick in GameHandler.allBricks) {
                    brick.draw(canvas)
                    //canvas.drawBitmap(paintedBrick, 1000f, 1000f, null)

                }
            }

            mHolder!!.unlockCanvasAndPost(canvas)
        }

        private fun intersects() {
            val widthPerZone = abs(paddle.width) / abs(6)
            if (ball.posY >= bounds.bottom.toFloat() - Setting.screenHeight / 6f - 100f && !hasScore && ball.speedY < 0 && Setting.gameMode == 0) {

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

            if (Setting.gameMode == 0) {
                playActivity.updateScore("Total score: $score")
            }
            if (Setting.gameMode == 1) {
                playActivity.updateScore("Total score: $score")
            }



            if (ball.posY + ball.size > bounds.bottom) {
                running = false
                playActivity.showGameOver(Setting.gameMode)
                playActivity.updateLevel(android.R.color.transparent)
                Setting.score = score


            }

        }

        override fun surfaceCreated(p0: SurfaceHolder) {

        }

        override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

            bounds = Rect(0, 0, p2, p3)

            paddle.posY = bounds.bottom.toFloat() - Setting.screenHeight / 6f
            ball.posY = bounds.bottom.toFloat() - Setting.screenHeight / 6f - 50f

            start()
        }

        override fun surfaceDestroyed(p0: SurfaceHolder) {
            stop()
        }

        override fun run() {
            while (running && !Setting.rageQuit) {
                draw()
                intersects()
                update()
                ball.checkBounds(bounds)
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
                    ball.speedX = -0f
                    ball.speedY = -40f
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
    }