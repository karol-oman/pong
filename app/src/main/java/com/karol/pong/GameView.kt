package com.karol.pong

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.rotationMatrix
import androidx.core.graphics.scaleMatrix

class GameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback, Runnable {

    private var thread: Thread? = null
    private var running = false
    lateinit var canvas: Canvas
    private lateinit var ball: Ball

    private var score: Int = 0

    private lateinit var paddle: Paddle
    private var bounds = Rect()

    private var mHolder: SurfaceHolder? = holder


    private val random = (0..6).random()

    private val imgId = arrayOf(
        R.drawable.backgroundoneblur, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4,
        R.drawable.bg5, R.drawable.bg6, R.drawable.bg7
    )


    //binding.layoutMain.setBackgroundResource(imgId[random])

    private var background: Bitmap = BitmapFactory.decodeResource(resources, imgId[random])
    //var background: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.treeboardbetter_jpg)


    init {
        if (mHolder != null)
            mHolder?.addCallback(this)
        setup()
    }

    private fun setup() {

        //Random xPOS
        val random = (-5..5).random()

        //Creates ball and paddle objects
        ball = Ball(this.context, 50f, 100f, 50f, 30f, 40f)
        paddle = Paddle(this.context)

        //Starting position for ball and paddle
        ball.posY = 100f
        ball.posX = 500f
        paddle.posX = 500f

        //Sets the color to ball and paddle.
        ball.paint.color = Color.YELLOW
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

    private fun draw() {

        canvas = mHolder!!.lockCanvas()


        //Matrix(R.drawable.bg2)
        //Drawing background to canvas
        //.

        canvas.drawBitmap(background, matrix, null)



        paddle.draw(canvas)

        ball.draw(canvas)
        mHolder!!.unlockCanvasAndPost(canvas)


    }

//TODO SHOW THIS TMRW
//    fun checkPaddleHit(){
//        if (ball.posY + ball.size > paddle.paddle.top){
//            ball.speedY *= -1
//        }
//    }


    private fun intersects() {

        if (RectF.intersects(paddle.paddle, ball.hitbox)) {

            ball.speedY *= -1f

            //Increase difficulty based on score.
            when (score) {
                10 -> ball.speedY = -80f
                20 -> ball.speedY = -110f
                40 -> ball.speedY = -150f
                80 -> ball.speedY = -200f
            }

            score++

            println("Total score: $score")



        }
        if (ball.posY + ball.size > bounds.bottom) {


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

        return true
    }

}