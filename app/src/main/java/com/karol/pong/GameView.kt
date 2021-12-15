package com.karol.pong

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.graphics.scale

class GameView(context: Context?, private val gameMode: Int, ballId: Int) : SurfaceView(context), SurfaceHolder.Callback, Runnable {

    private var thread: Thread? = null
    private var running = false
    lateinit var canvas: Canvas
    private lateinit var ball: Ball
    private lateinit var bricks: Bricks

    private var score: Int = 0

    private var playActivity = context as PlayActivity

    private lateinit var paddle: Paddle
    private var bounds = Rect()

    private var mHolder: SurfaceHolder? = holder

    private val randomBackground = (0..6).random()
    //private val randomBall = (0..1).random()

    private val imgId = arrayOf(
        R.drawable.backgroundoneblur, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4,
        R.drawable.bg5, R.drawable.bg6, R.drawable.bg7
    )

    private val ballArray = arrayOf(R.drawable.balll1, R.drawable.ball2,R.drawable.ball3,R.drawable.ball4, R.drawable.ball5)


    private var background: Bitmap = BitmapFactory.decodeResource(resources, imgId[randomBackground])
        .scale(getScreenWidth(), getScreenHeight())


    private var paintedBall: Bitmap = BitmapFactory.decodeResource(resources, ballArray[ballId]).scale(100, 100, true)

    init {

        println("GAMEMODE $gameMode")

        if (mHolder != null)
            mHolder?.addCallback(this)


        setup()
    }

    private fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    private fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }


    private fun setup() {

        //Random xPOS
        //val random = (-5..5).random()

        //Creates ball and paddle objects
        ball = Ball(this.context, 50f, 100f, 50f, 30f, 40f)
        paddle = Paddle(this.context)

        bricks = Bricks(300f, 600f)


        //Starting position for ball and paddle
        ball.posY = 100f
        ball.posX = 500f
        paddle.posX = 500f

        //Sets the color to ball and paddle.
        ball.paint.color = Color.TRANSPARENT
        paddle.paint.color = Color.GREEN
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


    }
    fun restartGame(){
        running = true
    }

    private fun draw() {

        canvas = mHolder!!.lockCanvas()


        //Drawing background to canvas
        canvas.drawBitmap(background, matrix, null)

        paddle.draw(canvas)

        ball.draw(canvas)
        canvas.drawBitmap(paintedBall, ball.posX - ball.size, ball.posY - ball.size, null)


        if(gameMode == 1){

            bricks.draw(canvas)

            val list: ArrayList<Bricks> = ArrayList()

            var xpos = 0f

            for (i in 0..10) {


                //TODO DISPLAY METRICS HERE

                    val brick = Bricks(xpos, 100f)
                list.add(brick)
                xpos += 250f

                //println("LISTSIZE ${list.size}")


                brick.draw(canvas)

            }


        }

        mHolder!!.unlockCanvasAndPost(canvas)


    }

    private fun intersects() {

        if(gameMode == 1){
            if(RectF.intersects(ball.hitbox, bricks.bricks)){
                println("Träffade bricken, wohoooo")
                ball.speedY *= -1f
                //bricks.destroyBrick(canvas)
                bricks.width = 0f
                bricks.height = 0f
            }
        }


        if (RectF.intersects(paddle.paddle, ball.hitbox)) {

            ball.speedY *= -1f

            //Increase difficulty based on score.
            when (score + 1) {
                1 -> playActivity.updateLevel("")
                10 -> {
                    ball.speedY = -80f
                    playActivity.updateLevel("Level: 2")
                }
                11 -> playActivity.updateLevel("")

                20 -> {
                    ball.speedY = -110f
                    playActivity.updateLevel("Level: 3")
                }
                21 -> playActivity.updateLevel("")
                40 -> {
                    ball.speedY = -150f
                    playActivity.updateLevel("Level: 4")
                }
                41 -> playActivity.updateLevel("")
                80 -> {
                    ball.speedY = -200f
                    playActivity.updateLevel("Level: 5")
                }
                81 -> playActivity.updateLevel("")
            }

            score++

            println("Total score: $score")
            playActivity.updateScore("Total score: $score")
        }
        if (ball.posY + ball.size > bounds.bottom) {
            playActivity.showGameOver(score)
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