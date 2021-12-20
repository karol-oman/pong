package com.karol.pong


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.karol.pong.databinding.ActivityPlayBinding
import com.karol.pong.fragments.GameFragment
import com.karol.pong.fragments.GameOverFragment
import java.lang.Exception

class PlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.commit {
            add(R.id.frame_play, GameFragment())
        }

        val dataController = DataController(this);
        var text = "Highscore: ${dataController.highestScore().score}"

        binding.textViewHighScore.text = text



    }

    fun showGameOver(score : Int, gameMode:Int) {

        runOnUiThread(Runnable {
            supportFragmentManager.commit {
                add(R.id.frame_play, GameOverFragment(applicationContext, score, gameMode))
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