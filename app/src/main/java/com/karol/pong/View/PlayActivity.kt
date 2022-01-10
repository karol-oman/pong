package com.karol.pong.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.karol.pong.Controller.DataController
import com.karol.pong.Model.Setting
import com.karol.pong.R
import com.karol.pong.databinding.ActivityPlayBinding
import com.karol.pong.Fragment.GameFragment
import com.karol.pong.Fragment.GameOverFragment


class PlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.commit {
            add(R.id.frame_play, GameFragment())
        }

        val dataController = DataController(this)

        val text = "High score: ${dataController.highestScore(Setting.gameMode).score}"
        binding.textViewHighScore.text = text

    }

    override fun onBackPressed() {
        super.onBackPressed()

        Setting.rageQuit = true

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    fun showGameOver(gameMode: Int) {

        runOnUiThread(Runnable {
            supportFragmentManager.commit {
                add(R.id.frame_play, GameOverFragment(applicationContext, gameMode))
            }
        })
    }

    fun updateScore(str: String) {
        runOnUiThread(Runnable {
            binding.textViewCurrentScore.text = str


        })
    }

    fun updateLevel(id: Int) {
        runOnUiThread(Runnable {
            binding.textViewLvl.setBackgroundResource(id)

        })
    }

    fun updateLevelText(str : String) {
        runOnUiThread(Runnable {
            binding.textViewLevel.text = str

        })
    }
}