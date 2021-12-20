package com.karol.pong

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.graphics.scale

class GameView(context: Context?) : SurfaceView(context), SurfaceHolder.Callback, Runnable {

    private var thread: Thread? = null
    private var running = false
    lateinit var canvas: Canvas
    lateinit var ball: Ball
    private lateinit var bricks: Bricks

    private var score: Int = 0

    private var playActivity = context as PlayActivity

    private lateinit var paddle: Paddle
    private var bounds = Rect()

    private var mHolder: SurfaceHolder? = holder

    private val randomBackground = (0..6).random()

    private val gameMode = Setting.gameMode

    private val imgId = arrayOf(
        R.drawable.backgroundoneblur, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4,
        R.drawable.bg5, R.drawable.bg6, R.drawable.bg7
    )

    private val ballArray = arrayOf(R.drawable.balll1, R.drawable.ball2,R.drawable.ball3,R.drawable.ball4, R.drawable.ball5)

    private val paddleArray = arrayOf(R.drawable.bamboo, R.drawable.chopsticks, R.drawable.bowl)

    private var background: Bitmap = BitmapFactory.decodeResource(resources, imgId[randomBackground])
        .scale(getScreenWidth(), getScreenHeight())


    private var paintedBall: Bitmap = BitmapFactory.decodeResource(resources, ballArray[Setting.ballID]).scale(110, 110, true)

    private var paintedPaddle: Bitmap = BitmapFactory.decodeResource(resources, paddleArray[Setting.paddleID]).scale(500,50, true)
    init {

        if (mHolder != null)
            mHolder?.addCallback(this)

        println("running $running")

        setup()
    }
    private fun generateBricks(){
        var xpos = 0f

        for (i in 0..6){

            val brick = Bricks(0f,0f)

            val brick1 = Bricks(xpos, brick.height )
            GameHandler.allBricks.add(brick1)

            val brick2 = Bricks(xpos, brick.height*2 + brick.height/2)
            GameHandler.allBricks.add(brick2)

            val brick3 = Bricks(xpos, brick.height*4)
            GameHandler.allBricks.add(brick3)

            val brick4 = Bricks(xpos, brick.height*5 + brick.height/2)
            GameHandler.allBricks.add(brick4)

            xpos += 250f

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
        generateBricks()

        //Creates ball and paddle objects
        ball = Ball(this.context, 800f, 600f, 30f, 20f, 20f)
        paddle = Paddle(this.context)

        //Starting position for ball and paddle
//        if(gameMode == 1){
//            ball.posY = 900f
//        }
//        else ball.posY = 200f


        paddle.posX = 500f

        //Sets the color to ball and paddle.
        ball.paint.color = Color.LTGRAY


        paddle.paint1.color = Color.TRANSPARENT
        paddle.paint2.color = Color.TRANSPARENT
        paddle.paint3.color = Color.TRANSPARENT
        paddle.paint4.color = Color.TRANSPARENT
        paddle.paint5.color = Color.TRANSPARENT
        paddle.paint6.color = Color.TRANSPARENT
        paddle.paint7.color = Color.TRANSPARENT
        //changes color on paddle
        /*paddle.paint1.color = Color.WHITE
        paddle.paint2.color = Color.GREEN
        paddle.paint3.color = Color.BLUE
        paddle.paint4.color = Color.YELLOW
        paddle.paint5.color = Color.CYAN
        paddle.paint6.color = Color.RED
        paddle.paint7.color = Color.MAGENTA */
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
        for(brick in GameHandler.allBricks){
            brick.update(ball)
        }
        val x = GameHandler.allBricks.toMutableList()

        for (brick in x){
            if (brick.destroy){
                GameHandler.allBricks.remove(brick)
            }
        }

    }

    private fun draw() {

        canvas = mHolder!!.lockCanvas()

        //Drawing background to canvas
        canvas.drawBitmap(background, matrix, null)

        paddle.draw(canvas)

        ball.draw(canvas)

//        canvas.drawBitmap(paintedBall, ball.posX - ball.size * 2f, ball.posY - ball.size * 2f, null)
        canvas.drawBitmap(paintedBall, ball.hitbox.left - 23, ball.hitbox.top - 23, null)
        canvas.drawBitmap(paintedPaddle, paddle.zone1.left, paddle.zone7.top,null )

        if(gameMode == 1){

            //TODO DISPLAY METRICS HERE

                for (brick in GameHandler.allBricks) {
                brick.draw(canvas)
            }

        }

        mHolder!!.unlockCanvasAndPost(canvas)
    }
    private fun intersects() {

        if (RectF.intersects(paddle.zone1, ball.hitbox)) {
            println("Hit zone 1")
            score++
            ball.speedY *= -1f
        }
        else if (RectF.intersects(paddle.zone2, ball.hitbox)) {
            println("Hit zone 2")
            score++
            ball.speedY *= -1f
        }
        else if (RectF.intersects(paddle.zone3, ball.hitbox)) {
            println("Hit zone 3")
            score++
            ball.speedY *= -1f
        }
        else if (RectF.intersects(paddle.zone4, ball.hitbox)) {
            println("Hit zone 4")
            score++
            ball.speedY *= -1f
        }
        else if (RectF.intersects(paddle.zone5, ball.hitbox)) {
            println("Hit zone 5")
            score++
            ball.speedY *= -1f
        }
        else if (RectF.intersects(paddle.zone6, ball.hitbox)) {
            println("Hit zone 6")
            score++
            ball.speedY *= -1f
        }
        else if (RectF.intersects(paddle.zone7, ball.hitbox)) {
            println("Hit zone 7")
            score++
            ball.speedY *= -1f
        }



//        if (RectF.intersects(paddle.paddle, ball.hitbox)) {
//
//            ball.speedY *= -1f
//
//            //Increase difficulty based on score.
//            when (score + 1) {
//                1 -> playActivity.updateLevel("")
//                10 -> {
//                    ball.speedY = -80f
//                    playActivity.updateLevel("Level: 2")
//                }
//                11 -> playActivity.updateLevel("")
//
//                20 -> {
//                    ball.speedY = -110f
//                    playActivity.updateLevel("Level: 3")
//                }
//                21 -> playActivity.updateLevel("")
//                40 -> {
//                    ball.speedY = -150f
//                    playActivity.updateLevel("Level: 4")
//                }
//                41 -> playActivity.updateLevel("")
//                80 -> {
//                    ball.speedY = -200f
//                    playActivity.updateLevel("Level: 5")
//                }
//                81 -> playActivity.updateLevel("")
//            }
//


          println("Total score: $score")
          playActivity.updateScore("Total score: $score")

//        }
        if (ball.posY + ball.size > bounds.bottom) {
            playActivity.showGameOver(score, gameMode)
            playActivity.updateLevel("")
            println("u suck")
            running = false
        }


        //if(RectF.intersects(paddle.paddle, RectF(bounds.bottom))){
    }


    override fun surfaceCreated(p0: SurfaceHolder) {

    }
    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

        bounds = Rect(0, 0, p2, p3)

        paddle.posY = bounds.bottom.toFloat() - 100f

        start()
    }
    override fun surfaceDestroyed(p0: SurfaceHolder) {
        stop()
    }
    override fun run() {
        while (running) {
            update()
            draw()
            intersects()
            ball.checkBounds(bounds)

        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        paddle.posX = event!!.x

        //Sets the position of paddle to right of screen if paddle goes "outside" screen
        if (paddle.posX + paddle.width > bounds.right) {
            paddle.posX = bounds.right.toFloat() - paddle.width
        }
        return true
    }

}