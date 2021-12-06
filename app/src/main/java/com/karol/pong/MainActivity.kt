package com.karol.pong

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karol.pong.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val random = (0..1).random()

        val imgId = arrayOf(R.drawable.backgroundoneblur, R.drawable.treeboardbetter_jpg)

        binding.layoutMain.setBackgroundResource(imgId[random])


        binding.buttonPlay.setOnClickListener() {
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
        }

        binding.buttonSettings.setOnClickListener() {

            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.buttonScores.setOnClickListener() {


            val intent = Intent(this, HighScoreActivity::class.java)
            startActivity(intent)

        }

    }


}