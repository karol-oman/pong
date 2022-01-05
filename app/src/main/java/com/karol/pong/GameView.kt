package com.karol.pong


import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.graphics.scale
import kotlin.math.abs


class GameView(context: Context?) : SurfaceView(context), SurfaceHolder.Callback, Runnable {

    private var thread: Thread? = null
    private var running = false
    lateinit var canvas: Canvas
    lateinit var ball: Ball

    private var score: Int = 0

    private var playActivity = context as PlayActivity

    private lateinit var paddle: Paddle
    private var bounds = Rect()

    private var mHolder: SurfaceHolder? = holder

    private val randomBackground = (0..6).random()

    private val randomBallYSpeed = (-30..30).random().toFloat()
    private val randomBallXSpeed = (-30..30).random().toFloat()

    private val imgId = arrayOf(
        R.drawable.backgroundoneblur, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4,
        R.drawable.bg5, R.drawable.bg6, R.drawable.bg7
    )

    private val ballArray = arrayOf(R.drawable.balll1, R.drawable.ball2,R.drawable.ball3,R.drawable.ball4, R.drawable.shuri)

    private val paddleArray = arrayOf(R.drawable.bamboo, R.drawable.chopsticks, R.drawable.bowl)

    private val brickArray = arrayOf(R.drawable.paddle_kiwii, R.drawable.paddle_dragon, R.drawable.paddle_green_apple,R.drawable.paddle_purple_apple, R.drawable.paddle_strawberry, R.drawable.paddle_watermelon)

    private var hasStarted = false

    private var background: Bitmap = BitmapFactory.decodeResource(resources, imgId[randomBackground])
        .scale(getScreenWidth(), getScreenHeight())
    private var paintedBall: Bitmap = BitmapFactory.decodeResource(resources, ballArray[Setting.ballID]).scale(110, 110, true)

    private var paintedPaddle: Bitmap = BitmapFactory.decodeResource(resources, paddleArray[Setting.paddleID]).scale(500,50, true)

    private var paintedBrick: Bitmap = BitmapFactory.decodeResource(resources, brickArray[(0..5).random()]).scale(getScreenWidth()/14 + (getScreenWidth()/14 * 2)/12, 50, true)



    init {

        if (mHolder != null)
            mHolder?.addCallback(this)

        println("running $running")

        setup()
    }
    private fun generateBricks(){
        var xpos = +10f
        val ypos = 70f

        val margin = 10f


        for (i in 0..11){


            val brick = Bricks(0f,0f, paintedBrick )

            val brick1 = Bricks(xpos, brick.height + ypos, paintedBrick )
            GameHandler.allBricks.add(brick1)

            val brick2 = Bricks(xpos, brick.height*2 + margin + ypos, paintedBrick )
            GameHandler.allBricks.add(brick2)

            val brick3 = Bricks(xpos, brick.height*3 + (margin * 2) + ypos, paintedBrick )
            GameHandler.allBricks.add(brick3)

            val brick4 = Bricks(xpos, brick.height*4 + (margin * 3) + ypos, paintedBrick )
            GameHandler.allBricks.add(brick4)

            val brick5 = Bricks(xpos, brick.height*5 + (margin * 4) + ypos, paintedBrick )
            GameHandler.allBricks.add(brick5)

            val brick6 = Bricks(xpos, brick.height*6 + (margin * 5) + ypos, paintedBrick )
            GameHandler.allBricks.add(brick6)



            xpos += getScreenWidth()/14 + (getScreenWidth()/14 * 2)/12

//            val brick1 = Bricks(getScreenWidth()/12f-margin, brick.height + 10f)
//            GameHandler.allBricks.add(brick1)

//            val brick2 = Bricks(, brick.height + 10f)
//            GameHandler.allBricks.add(brick2)
        }

    }

    private fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    private fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }


    private fun setup() {

        //Generates bricks in game
        if(Setting.gameMode == 1)
            generateBricks()

        //Creates ball and paddle objects
        paddle = Paddle()
        paddle.posX = (getScreenWidth()/2f) - paddle.width / 2f
        ball = Ball(paddle.posX + paddle.width / 2, 1750f, 30f, 0f, 0f)

        println(getScreenWidth())




        //Sets the color to ball and paddle.
        ball.paint.color = Color.TRANSPARENT


        paddle.paint1.color = Color.TRANSPARENT
        paddle.paint2.color = Color.TRANSPARENT
        paddle.paint3.color = Color.TRANSPARENT
        paddle.paint4.color = Color.TRANSPARENT
        paddle.paint5.color = Color.TRANSPARENT
        paddle.paint6.color = Color.TRANSPARENT
        paddle.paint7.color = Color.TRANSPARENT

        //changes color on paddle
//        paddle.paint1.color = Color.WHITE
//        paddle.paint2.color = Color.GREEN
//        paddle.paint3.color = Color.BLUE
//        paddle.paint4.color = Color.YELLOW
//        paddle.paint5.color = Color.CYAN
//        paddle.paint6.color = Color.RED
//        paddle.paint7.color = Color.MAGENTA

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


        ball.update()

        if(Setting.gameMode == 1){

            for(brick in GameHandler.allBricks){
                brick.update(ball)
            }
            val x = GameHandler.allBricks.toMutableList()

            for (brick in x){
                if (brick.destroy){
                    GameHandler.allBricks.remove(brick)
                    Setting.test++
                    println("TOTAL BRICKS" + GameHandler.allBricks.size)

                }
            }

            if(ball.posY < 1500f)
                println("under 1500")

            //println("TOTAL BRICKS" + GameHandler.allBricks.size)

            if(GameHandler.allBricks.isEmpty() && ball.posY > (getScreenHeight()/2).toFloat()){

                //Setting.level++
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
        canvas.drawBitmap(paintedPaddle, paddle.paddle.left, paddle.paddle.top,null )

        if(Setting.gameMode == 1){

            for (brick in GameHandler.allBricks) {
                brick.draw(canvas)
                //canvas.drawBitmap(paintedBrick, 1000f, 1000f, null)

            }
        }

        mHolder!!.unlockCanvasAndPost(canvas)
    }
    private fun intersects() {
        val widthPerZone = paddle.width/6

                if(RectF.intersects(paddle.paddle, ball.hitbox)){

                    ball.speedY *= -1f
                    val ballTotalSpeed = abs(ball.speedY) + abs(ball.speedX)

                    if ((ball.hitbox.centerX() < paddle.posX + widthPerZone)){
                        println("zon1")
                        ball.speedX = (ballTotalSpeed * 0.8f) * -1
                        ball.speedY = (ballTotalSpeed * 0.2f) * -1

                    }
                    else if ((ball.hitbox.centerX() < paddle.posX + widthPerZone*2)){
                        println("zon2")
                        ball.speedX = (ballTotalSpeed * 0.7f) * -1
                        ball.speedY = (ballTotalSpeed * 0.3f) * -1

                    }
                    else if ((ball.hitbox.centerX() < paddle.posX + widthPerZone*3)){
                        println("zon3")
                        ball.speedX = (ballTotalSpeed * 0.6f) * -1
                        ball.speedY = (ballTotalSpeed * 0.4f) * -1

                    }
                    else if ((ball.hitbox.centerX() < paddle.posX + widthPerZone*4)){
                        println("zon4")
                        ball.speedX = (ballTotalSpeed * 0.6f)
                        ball.speedY = (ballTotalSpeed * 0.4f) * -1

                    }
                    else if ((ball.hitbox.centerX() < paddle.posX + widthPerZone*5)){
                        println("zon5")
                        ball.speedX = (ballTotalSpeed * 0.7f)
                        ball.speedY = (ballTotalSpeed * 0.3f) * -1

                    }
                    else if ((ball.hitbox.centerX() <= paddle.posX + widthPerZone*6)){
                        println("zon6")
                        ball.speedX = (ballTotalSpeed * 0.8f)
                        ball.speedY = (ballTotalSpeed * 0.2f) * -1

                    }
                    else if(ball.hitbox.centerY() + ball.width <= paddle.posY ){
                        println("träffade kanten")

                    }

        if(Setting.gameMode == 0){


                when (score) {

                    0 -> {
                        playActivity.updateLevel(android.R.color.transparent)
                        playActivity.updateLevelText("Level: 1")
                    }
                    10 -> {
                        ball.speedY = -80f
                        playActivity.updateLevel(R.drawable.levelup)
                        playActivity.updateLevelText("Level: 2")
                    }
                    2 -> {
                        playActivity.updateLevel(android.R.color.transparent)

                    }
                    20 -> {
                        ball.speedY = -110f
                        playActivity.updateLevel(R.drawable.levelup)
                        playActivity.updateLevelText("Level: 3")
                    }
                    21 -> playActivity.updateLevel(android.R.color.transparent)

                    30 -> {
                        ball.speedY = -150f
                        playActivity.updateLevel(R.drawable.levelup)
                        playActivity.updateLevelText("Level: 4")
                    }
                    31 -> playActivity.updateLevel(android.R.color.transparent)

                    50 -> {
                        ball.speedY = -200f
                        playActivity.updateLevel(R.drawable.levelup)
                        playActivity.updateLevelText("Level: 4")
                    }
                    51 -> playActivity.updateLevel(android.R.color.transparent)
                }
            }
        }

        if(Setting.gameMode == 0){
            playActivity.updateScore("Total score: $score")
        }
        if(Setting.gameMode == 1){
            playActivity.updateScore("Total score: ${Setting.test}")
        }



        if (ball.posY + ball.size > bounds.bottom) {
            playActivity.showGameOver(Setting.gameMode)
            playActivity.updateLevel(android.R.color.transparent)
            Setting.score = score
            running = false


        }

    }
    override fun surfaceCreated(p0: SurfaceHolder) {

    }
    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

        bounds = Rect(0, 0, p2, p3)

        paddle.posY = bounds.bottom.toFloat() - 400f
        ball.posY = bounds.bottom.toFloat() - 450f

        start()
    }
    override fun surfaceDestroyed(p0: SurfaceHolder) {
        stop()
    }
    override fun run() {
        while (running && !Setting.rageQuit) {
            update()
            draw()
            intersects()
            ball.checkBounds(bounds)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        paddle.posX = event!!.x - paddle.width/2

        //Sets the position of paddle & ball to right of screen if paddle goes "outside" screen
        if (paddle.posX + paddle.width > bounds.right) {
            paddle.posX = bounds.right.toFloat() - paddle.width
        }
        if (paddle.posX < bounds.left) {
            paddle.posX = bounds.left.toFloat()
        }

        if (!hasStarted){
            ball.posX = event!!.x

            if (event!!.action == MotionEvent.ACTION_UP){

                hasStarted = true
                ball.posX = abs(paddle.posX) + abs(paddle.width) / abs(2)
                ball.speedX = 0f
                ball.speedY = -20f
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