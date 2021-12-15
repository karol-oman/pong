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
        val ballId = intent.getIntExtra("ballID", 0)
        println("BallID: $ballId")

        println("GameMode: $gameMode")

        val dataController = DataController(this);
        var text = "Highscore: ${dataController.highestScore().score}"
        binding.textViewHighScore.text = text



        supportFragmentManager.commit {
            add(R.id.frame_play, GameFragment(gameMode, ballId))
        }

    }

    fun showGameOver(score : Int) {

        runOnUiThread(Runnable {
            supportFragmentManager.commit {
                //this was R.id.test
                add(R.id.frame_play, GameOverFragment(applicationContext, score))
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