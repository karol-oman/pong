package com.karol.pong.view

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.karol.pong.R
import com.karol.pong.model.Setting
import com.karol.pong.databinding.ActivityMainBinding

/**
 *  @authors Sarah, Gustav, Karol, Calle
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Setting.rageQuit = false

        rotateStar()
        generateBackground()

        val toGame = Intent(this, PlayActivity::class.java)

        /**
         * On click listeners set for all the buttons in the main menu
         */

        binding.buttonPlay.setOnClickListener {

            Setting.gameMode = 0
            startActivity(toGame)
        }
        binding.buttonPlay2.setOnClickListener {

            Setting.gameMode = 1
            startActivity(toGame)
        }

        binding.buttonSettings.setOnClickListener {
            val toSettings = Intent(this, SettingsActivity::class.java)
            startActivity(toSettings)
        }

        binding.buttonScores.setOnClickListener {
            val toScores = Intent(this, HighScoreActivity::class.java)
            startActivity(toScores)
        }

    }


    /**
     * Method for rotating the shuriken in Pong
     */
    private fun rotateStar() {
        val rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)

        val rotImage = binding.rotatingShuriken

        rotImage.animation = rotate
    }
    /**
     * Method to generate a random background
     */
    private fun generateBackground() {

        val random = (0..6).random()
        val imgId = arrayOf(
            R.drawable.backgroundoneblur, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4,
            R.drawable.bg5, R.drawable.bg6, R.drawable.bg7
        )
        binding.layoutMain.setBackgroundResource(imgId[random])
    }

}