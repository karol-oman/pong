package com.karol.pong.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.karol.pong.controller.DataController
import com.karol.pong.model.Setting
import com.karol.pong.R
import com.karol.pong.databinding.ActivityPlayBinding
import com.karol.pong.fragment.GameFragment
import com.karol.pong.fragment.GameOverFragment

/**
 *  @authors Sarah, Gustav, Karol, Calle
 */

class PlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /**
         * Commits game fragment which then returns GameView
         */
        supportFragmentManager.commit {
            add(R.id.frame_play, GameFragment())
        }


        /**
         * Creates an instance of DataController to show the highest score
         */
        val dataController = DataController(this)

        val text = "High score: ${dataController.highestScore(Setting.gameMode).score}"
        binding.textViewHighScore.text = text

    }

    /**
     * Overrides the built in back button to create a new intent
     */
    override fun onBackPressed() {
        super.onBackPressed()

        Setting.rageQuit = true

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    /**
     * Shows the game over fragment
     */
    fun showGameOver(gameMode: Int) {

        runOnUiThread(Runnable {
            supportFragmentManager.commit {
                add(R.id.frame_play, GameOverFragment(applicationContext, gameMode))
            }
        })
    }

    /**
     * Creates a thread for updating the score
     */
    fun updateScore(str: String) {
        runOnUiThread(Runnable {
            binding.textViewCurrentScore.text = str


        })
    }

    /**
     * Creates a thread for updating the level up image
     */
    fun updateLevel(id: Int) {
        runOnUiThread(Runnable {
            binding.textViewLvl.setBackgroundResource(id)

        })
    }

    /**
     * Creates a thread for updating the level text
     */
    fun updateLevelText(str: String) {
        runOnUiThread(Runnable {
            binding.textViewLevel.text = str

        })
    }
}