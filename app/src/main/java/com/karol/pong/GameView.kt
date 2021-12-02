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

    private lateinit var paddle: Paddle

    var mHolder: SurfaceHolder? = holder

    var background: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.treeboardbetter_jpg)


    init {
        if (mHolder != null)
            mHolder?.addCallback(this)
        setup()
    }

    private fun setup() {
        ball = Ball(this.context)

        paddle = Paddle(this.context)

        ball.posY = 100f
        ball.posX = 100f

        ball.paint.color = Color.CYAN

        paddle.paint.color = Color.WHITE
        paddle.posX = 750f
        paddle.posY = 2200f


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


    override fun surfaceCreated(p0: SurfaceHolder) {

    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        start()
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        stop()
    }

    override fun run() {
        while (running) {
            update()
            draw()
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        //ball2.posX = event!!.x

        //ball2.posY = event!!.y

        paddle.posX = event!!.x

        return true
    }

}