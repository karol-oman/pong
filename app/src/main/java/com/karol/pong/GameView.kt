package com.karol.pong


import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.animation.AnimationUtils
import androidx.core.graphics.scale



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

    private val imgId = arrayOf(
        R.drawable.backgroundoneblur, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4,
        R.drawable.bg5, R.drawable.bg6, R.drawable.bg7
    )

    private val ballArray = arrayOf(R.drawable.balll1, R.drawable.ball2,R.drawable.ball3,R.drawable.ball4, R.drawable.shuri)

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

        for (i in 0..5){

            val brick = Bricks(0f,0f)

            val brick1 = Bricks(xpos, brick.height )
            GameHandler.allBricks.add(brick1)

            val brick2 = Bricks(xpos, brick.height*2 + brick.height/2)
            GameHandler.allBricks.add(brick2)

            val brick3 = Bricks(xpos, brick.height*4)
            GameHandler.allBricks.add(brick3)

            val brick4 = Bricks(xpos, brick.height*5 + brick.height/2)
            GameHandler.allBricks.add(brick4)

//            if (Setting.level == 1){
//                val brick5 = Bricks(xpos, brick.height*6 * brick.height/2)
//                GameHandler.allBricks.add(brick5)
//            }

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
        if(Setting.gameMode == 1)
            generateBricks()

        //Creates ball and paddle objects
        ball = Ball(500f, 800f, 30f, 30f, 40f)
        paddle = Paddle()

        paddle.posX = 500f


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
        canvas.drawBitmap(paintedPaddle, paddle.zone1.left, paddle.zone7.top,null )

        if(Setting.gameMode == 1){

            //TODO DISPLAY METRICS HERE

                for (brick in GameHandler.allBricks) {
                brick.draw(canvas)

            }


        }

        mHolder!!.unlockCanvasAndPost(canvas)
    }
    private fun intersects() {

        //TODO implement paddle zone behaviours in Sprint 3
        when {
            RectF.intersects(paddle.zone1, ball.hitbox) -> {
                //println("Hit zone 1")

                ball.speedY *= -1

//                ball.speedY = -(abs(Setting.totSpeed) * abs(0.3f))
//                ball.speedX = -(abs(Setting.totSpeed) * abs(0.7f))
                score++
            }
            RectF.intersects(paddle.zone2, ball.hitbox) -> {
                //println("Hit zone 2")

                ball.speedY *= -1
//                ball.speedY = -(abs(Setting.totSpeed) * abs(0.35f))
//                ball.speedX = -(abs(Setting.totSpeed) * abs(0.65f))
                score++
            }
            RectF.intersects(paddle.zone3, ball.hitbox) -> {
                //println("Hit zone 3")

                ball.speedY *= -1
//                ball.speedY = -(abs(Setting.totSpeed) * abs(0.4f))
//                ball.speedX = -(abs(Setting.totSpeed) * abs(0.6f))
                score++
            }
            RectF.intersects(paddle.zone4, ball.hitbox) -> {
                //println("Hit zone 4")

                ball.speedY *= -1
//                ball.speedY = -(abs(Setting.totSpeed) * abs(1f))
//                ball.speedX = (abs(Setting.totSpeed) * abs(0f))
                score++
            }
            RectF.intersects(paddle.zone5, ball.hitbox) -> {
                //println("Hit zone 5")

                ball.speedY *= -1
//                ball.speedY = -(abs(Setting.totSpeed) * abs(0.4f))
//                ball.speedX = (abs(Setting.totSpeed) * abs(0.6f))
                score++

            }
            RectF.intersects(paddle.zone6, ball.hitbox) -> {
                //println("Hit zone 6")

                ball.speedY *= -1
//                ball.speedY = -(abs(Setting.totSpeed) * abs(0.35f))
//                ball.speedX = (abs(Setting.totSpeed) * abs(0.65f))
                score++

            }
            RectF.intersects(paddle.zone7, ball.hitbox) -> {
                //println("Hit zone 7")

                ball.speedY *= -1
//                ball.speedY = -(abs(Setting.totSpeed) * abs(0.3f))
//                ball.speedX = (abs(Setting.totSpeed) * abs(0.7f))
                score++
            }
        }

        if(Setting.gameMode == 0){

            if (RectF.intersects(paddle.zone1, ball.hitbox) || RectF.intersects(paddle.zone2, ball.hitbox) ||
                RectF.intersects(paddle.zone3, ball.hitbox) || RectF.intersects(paddle.zone4, ball.hitbox) ||
                RectF.intersects(paddle.zone5, ball.hitbox) || RectF.intersects(paddle.zone6, ball.hitbox) ||
                RectF.intersects(paddle.zone7, ball.hitbox)){

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
            //playActivity.updateLevel("")
            Setting.score = score
            running = false

        }

    }
    override fun surfaceCreated(p0: SurfaceHolder) {

    }
    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

        bounds = Rect(0, 0, p2, p3)

        paddle.posY = bounds.bottom.toFloat() - 400f

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

        paddle.posX = event!!.x

        //Sets the position of paddle to right of screen if paddle goes "outside" screen
        if (paddle.posX + paddle.width > bounds.right) {
            paddle.posX = bounds.right.toFloat() - paddle.width
        }
        return true
    }

}