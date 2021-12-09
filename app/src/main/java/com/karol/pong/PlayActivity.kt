package com.karol.pong

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.View
import androidx.fragment.app.commit
import com.karol.pong.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {




    private lateinit var binding: ActivityPlayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.surfaceView.holder.addCallback(this)

        //binding.surfaceView.setOnTouchListener(this)


       supportFragmentManager.commit {
           add(R.id.frame_play, GameFragment())
       }

    }

    fun updateScore(str: String){
        runOnUiThread(Runnable {
            binding.textViewCurrentScore.text = str
        })
    }


}