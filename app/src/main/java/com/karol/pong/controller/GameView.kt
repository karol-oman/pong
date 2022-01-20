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

/**
 * The GameView displays and handles the visual elements in both of our game-modes
 *  @authors Sarah, Gustav, Karol, Calle
 */

class GameView(context: Context?) : SurfaceView(context), SurfaceHolder.Callback, Runnable {

    /**
     * Variables are initialized for the different game-objects
     */
    private var thread: Thread? = null
    private lateinit var canvas: Canvas
    private lateinit var ball: Ball
    private var running = false

    private var totalBrickRowWidth = Setting.screenWidth / 12 + (Setting.screenWidth / 12 * 2) / 10
    private var brickHeight = 70

    private var level = 0
    private var hasStarted = false

    private var playActivity = context as PlayActivity

    private lateinit var paddle: Paddle
    private var bounds = Rect()

    private var mHolder: SurfaceHolder? = holder



    /**
     * Creates arrays for our different images as drawables
     */
    private val imgId = arrayOf(
        R.drawable.backgroundoneblur,
        R.drawable.bg2,
        R.drawable.bg3,
        R.drawable.bg4,
        R.drawable.bg5,
        R.drawable.bg6,
        R.drawable.bg7
    )

    private val ballArray = arrayOf(
        R.drawable.ball5,
        R.drawable.ball2,
        R.drawable.ball3,
        R.drawable.ball4,
        R.drawable.shuri
    )

    private val paddleArray = arrayOf(
        R.drawable.bamboo,
        R.drawable.chopsticks,
        R.drawable.bowl
    )

    private val brickArray = arrayOf(
        R.drawable.paddle_dragon,
        R.drawable.paddle_green_apple,
        R.drawable.paddle_kiwii,
        R.drawable.paddle_purple_apple,
        R.drawable.paddle_strawberry,
        R.drawable.paddle_watermelon
    )


    /**
     * Creates the different bitmaps from our drawable arrays corresponding to the user setting
     * The bitmap for the background is randomized
     */
    private val randomBackground = (0..6).random()

    private var background: Bitmap =
        BitmapFactory.decodeResource(resources, imgId[randomBackground])
            .scale(Setting.screenWidth, Setting.screenHeight)
    private var paintedBall: Bitmap =
        BitmapFactory.decodeResource(resources, ballArray[Setting.ballID]).scale(110, 110, true)

    private var paintedPaddle: Bitmap =
        BitmapFactory.decodeResource(resources, paddleArray[Setting.paddleID])
            .scale(Setting.paddleWidth.toInt(), Setting.paddleHeight.toInt(), true)

    private val dragonfruit: Bitmap = BitmapFactory.decodeResource(resources, brickArray[0])
        .scale(totalBrickRowWidth, brickHeight, true)
    private val greenapple: Bitmap = BitmapFactory.decodeResource(resources, brickArray[1])
        .scale(totalBrickRowWidth, brickHeight, true)
    private val kiwi: Bitmap = BitmapFactory.decodeResource(resources, brickArray[2])
        .scale(totalBrickRowWidth, brickHeight, true)
    private val purpleapple: Bitmap = BitmapFactory.decodeResource(resources, brickArray[3])
        .scale(totalBrickRowWidth, brickHeight, true)
    private val strawberry: Bitmap = BitmapFactory.decodeResource(resources, brickArray[4])
        .scale(totalBrickRowWidth, brickHeight, true)
    private val watermelon: Bitmap = BitmapFactory.decodeResource(resources, brickArray[5])
        .scale(totalBrickRowWidth, brickHeight, true)

    /**
     * Initializes the surface-holder and calls the setup function
     */
    init {

        if (mHolder != null)
            mHolder?.addCallback(this)

        setup()
    }

    /**
     * Creates an instance of the IntersectHandler
     */

    private var intersectHandler = IntersectHandler(playActivity)

    /**
     * Creates an instance of the DrawHandler
     */

    private var drawHandler = DrawHandler()


    private fun generateBricks() {

        /**
         * Clears the GameHandler.paintArray
         */
        GameHandler.paintArray.clear()

        /**
         *  Generates bricks based on the strings in GameHandler
         *  When a player clears the board a new level is reached and a new level is created + the speed increases
         */
        when (level) {

            0 -> GameHandler.ball()
            1 -> {
                GameHandler.bill()
                ball.speedY *= Setting.speedMultiplier
            }
            2 -> {
                GameHandler.karolLevel()
                ball.speedY *= Setting.speedMultiplier
            }
            3 -> {
                GameHandler.japan()
                ball.speedY *= Setting.speedMultiplier
            }

            4 -> {
                GameHandler.labyrinth()
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
                GameHandler.bill()
                ball.speedY *= 2
            }


        }


        /**
         *  Clears the GameHandler.allBricks from any remainders of previous games
         */
        GameHandler.allBricks.clear()

        /**
         * Creates the bricks by looping through the paintarray 10 times,
         * Since we position the bricks from the top to bottom each string in
         * the paint array is called based on the current index on the loop
         * If the current string position contains any of the characters that corresponds a brick
         * the brick is added with the calculated margins.
         * If there's no recognizable character (e.g. ".") the margin of an "invisible" brick is added
         *
         */
        var xposBrick = 0f
        val yposTopMargin = 20f
        val margin = 10f

        for (i in 0..9) {

            var multiplyHeight = 1
            var multiplyMargin = 0

            for (string in GameHandler.paintArray) {

                //The height of a brick is 70f
                val total = (70f * multiplyHeight) + (margin * multiplyMargin) + yposTopMargin

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


    /**
     * Is called when the game is initialized. The score is set to zero
     * Depending on the game mode a level identifier is displayed or the bricks are generated
     */
    private fun setup() {

        Setting.score = 0

        if (Setting.gameMode == 0){
            playActivity.updateLevelText("Level: 1")
        }

        //Generates bricks in game
        else if (Setting.gameMode == 1){
            generateBricks()
        }


        //Creates ball and paddle objects
        paddle = Paddle()
        paddle.posX = (Setting.screenWidth / 2f) - paddle.width / 2f
        ball = Ball(paddle.posX + paddle.width / 2, paddle.posY, 30f, 0f, 0f, 50f)

    }

    /**
     * Sets the game flag to "running" and creates a thread for the GameView
     */
    private fun start() {
        running = true
        thread = Thread(this)
        thread?.start()
    }

    /**
     * Sets the game "running" flag to false and kills the thread
     */
    private fun stop() {
        running = false
        try {
            thread?.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    /**
     * If the ball happens to get stuck out of the right/left GameView-border we're moving it back by increments
     * If the game mode is Breakout and the GameHandler.allBricks is empty, we generate new bricks
     */
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

    /**
     * Initializes the canvas with the mHolder, paints the background and calls the DrawHandler to draw the
     * ball and paddle. Also creates the hitboxes and unlocks the canvas.
     */
    private fun draw() {

        canvas = mHolder!!.lockCanvas()

        //Drawing background to canvas
        canvas.drawBitmap(background, matrix, null)

        drawHandler.draw(canvas, ball, paddle)


        //Draws the bitmap for the ball and paddle and uses its hitbox as position
        canvas.drawBitmap(paintedBall, ball.hitbox.left - 23, ball.hitbox.top - 23, null)
        canvas.drawBitmap(paintedPaddle, paddle.paddle.left, paddle.paddle.top, null)


        playActivity.updateScore("Total score: ${Setting.score}")

        mHolder!!.unlockCanvasAndPost(canvas)
    }


    override fun surfaceCreated(p0: SurfaceHolder) {

    }

    /**
     * Sets the bounds for the GameView. Positions the paddle and the ball depending on the screen size.
     */
    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

        bounds = Rect(0, 0, p2, p3)

        paddle.posY = Setting.screenHeight - Setting.screenHeight / 6f
        ball.posY = Setting.screenHeight - Setting.screenHeight / 6f - 50f

        start()
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        stop()
    }

    /**
     * The game-loop. A game is run as long as the "running" flag is true and rageQuit is false
     * The different functions of the game is called while running
     */
    override fun run() {
        while (running && !Setting.rageQuit) {
            draw()
            intersectHandler.intersects(ball, paddle, bounds)
            update()
            intersectHandler.checkBounds(ball, bounds)
            checkIfDead()


        }
    }

    /**
     * Overrides the onTouch event with a paddle event. The user is able to hold down the finger and move the
     * paddle to their desired position before releasing, the ACTION_UP event is then called which sets the ball moving
     */
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

    /**
     * If the ball is at the bottom the game is lost and the "running" flag is set to false,
     * Displays the game over fragment
     */
    private fun checkIfDead() {
        if (ball.posY + ball.size > bounds.bottom) {
            running = false
            playActivity.showGameOver(Setting.gameMode)
            playActivity.updateLevel(android.R.color.transparent)
            //Setting.score = Setting.score

        }
    }
}