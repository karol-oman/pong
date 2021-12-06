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

    private lateinit var ball2: Ball

    private lateinit var paddle: Paddle
    private var bounds = Rect()

    var mHolder: SurfaceHolder? = holder

    var background: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.treeboardbetter_jpg)


    init {
        if (mHolder != null)
            mHolder?.addCallback(this)
        setup()
    }

    private fun setup() {

        //Random xPOS
        val random = (-5..5).random()

        ball = Ball(this.context, 50f, 100f, 50f, 20f, 20f)
        //ball2 = Ball(this.context, 100f, 100f, 50f, random.toFloat(), 10f)

        paddle = Paddle(this.context)

        ball.posY = 100f
        ball.posX = 100f

        ball.paint.color = Color.CYAN
        paddle.paint.color = Color.WHITE

        paddle.posX = 500f



    }

    fun start() {
        running = true
        thread = Thread(this)
        thread?.start()
    }

    fun stop() {
        running = false
        try {
            thread?.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun update() {
        ball.update()
        //paddle.update()
    }

    fun draw() {

        canvas = mHolder!!.lockCanvas()

        //Drawing background to canvas.
        canvas.drawBitmap(background, matrix, null)

        paddle.draw(canvas)

        ball.draw(canvas)
        mHolder!!.unlockCanvasAndPost(canvas)


    }


    fun checkPaddleHit(){
        if (ball.posY + ball.size > paddle.paddle.top){
            ball.speedY *= -1
        }
    }

    private fun intersects(){

        if(RectF.intersects(paddle.paddle ,ball.hitbox)){

            println("HIT POWEPOW")
            ball.speedY *= -1
            ball.speedX *= -1

        }
        //if(RectF.intersects(paddle.paddle, RectF(bounds.bottom))){
    }

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

            checkPaddleHit()
            intersects()

            ball.checkBounds(bounds)

        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        paddle.posX = event!!.x

        return true
    }

}