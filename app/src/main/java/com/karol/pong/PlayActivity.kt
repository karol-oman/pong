package com.karol.pong

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.View
import com.karol.pong.databinding.ActivityMainBinding
import com.karol.pong.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity(), SurfaceHolder.Callback, View.OnTouchListener {

    var circleX : Float = 100f
    var circleY : Float = 100f
    val ballPaint: Paint = Paint()


    private lateinit var binding: ActivityPlayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.surfaceView.holder.addCallback(this)

        binding.surfaceView.setOnTouchListener(this)



    }

    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean{
        drawBall()
        return true
    }

    override fun surfaceCreated(holder: SurfaceHolder) {

    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {

    }

    private fun drawBall(){
        val canvas: Canvas? = binding.surfaceView.holder.lockCanvas()
        val surfaceBackground = Paint()
        surfaceBackground.color = Color.WHITE

        canvas?.drawRect(0f,0f, binding.surfaceView.width.toFloat(), binding.surfaceView.height.toFloat(), surfaceBackground)

        ballPaint.color = Color.RED
        canvas?.drawCircle(circleX, circleY, 100f, ballPaint)

        binding.surfaceView.holder.unlockCanvasAndPost(canvas)
        binding.surfaceView.setZOrderOnTop(true)
    }
}