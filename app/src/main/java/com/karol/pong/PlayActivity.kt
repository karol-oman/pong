package com.karol.pong


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    fun showGameOver() {

        runOnUiThread(Runnable {
            supportFragmentManager.commit {
                add(R.id.test, GameOverFragment())
            }
        })
    }

    fun updateScore(str: String) {
        runOnUiThread(Runnable {
            binding.textViewCurrentScore.text = str

        })
    }

    fun updateLevel(str: String) {
        runOnUiThread(Runnable {
            binding.textViewLvl.text = str

        })
    }


}