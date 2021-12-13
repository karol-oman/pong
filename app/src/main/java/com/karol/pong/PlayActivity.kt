package com.karol.pong


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.karol.pong.databinding.ActivityPlayBinding
import com.karol.pong.fragments.GameFragment
import com.karol.pong.fragments.GameOverFragment

class PlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.surfaceView.holder.addCallback(this)

        //binding.surfaceView.setOnTouchListener(this)
        val gameMode = intent.getIntExtra("gamemode", 0)

        println(gameMode)



        supportFragmentManager.commit {
            add(R.id.frame_play, GameFragment(gameMode))
        }

    }

    fun showGameOver() {

        runOnUiThread(Runnable {
            supportFragmentManager.commit {
                //this was R.id.test
                add(R.id.frame_play, GameOverFragment())
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