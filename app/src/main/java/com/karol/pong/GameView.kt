package com.karol.pong

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback, Runnable {

    private var thread: Thread? = null
    private var running = false
    lateinit var canvas: Canvas
    private lateinit var ball: Ball

    private var score: Int = 0

    private lateinit var paddle: Paddle
    private var bounds = Rect()

    var mHolder: SurfaceHolder? = holder

    //var background: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.treeboardbetter_jpg)
    var background: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.bg5)


    init {
        if (mHolder != null)
            mHolder?.addCallback(this)
        setup()
    }

    private fun setup() {

        //Random xPOS
        val random = (-5..5).random()

        //Creates ball and paddle objects
        ball = Ball(this.context, 50f, 100f, 50f, 50f, 50f)
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

        //Drawing background to canvas.
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


    private fun intersects(){

        if(RectF.intersects(paddle.paddle ,ball.hitbox)){

            println("HIT POW POW")
            ball.speedY *= -1
            score++
            println("Total score: $score")
            //ball.speedX *= -1
        }
        if (ball.posY + ball.size > bounds.bottom){
            //speedY *= -1

            println("u suck")
            running = false
        }


        //if(RectF.intersects(paddle.paddle, RectF(bounds.bottom))){
    }


    //TODO we're not using this.
    fun bounceBall(b1: Ball, b2: Ball){
        b1.speedY *= -1
        b2.speedX = 0f

    }


    override fun surfaceCreated(p0: SurfaceHolder) {

    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

        bounds = Rect(0, 0, p2, p3)

        paddle.posY = bounds.bottom.toFloat() -100f
        start()
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        stop()
    }

    override fun run() {
        while (running) {
            update()
            draw()

            //checkPaddleHit()
            intersects()

            ball.checkBounds(bounds)

        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        paddle.posX = event!!.x

        return true
    }

}