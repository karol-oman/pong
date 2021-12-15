package com.karol.pong

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.karol.pong.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rotateStar()
        generateBackground()


        val ballId = intent.getIntExtra("ballID", 0)

        val intent = Intent(this, PlayActivity::class.java)

        binding.buttonPlay.setOnClickListener {
            //val intent = Intent(this, PlayActivity::class.java)

            intent.putExtra("gamemode", 0)
            intent.putExtra("ballID", ballId)

            startActivity(intent)
        }
        binding.buttonPlay2.setOnClickListener {
            //val intent = Intent(this, PlayActivity::class.java)
            intent.putExtra("gamemode", 1)
            intent.putExtra("ballID", ballId)
            startActivity(intent)
        }

        binding.buttonSettings.setOnClickListener {

            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.buttonScores.setOnClickListener {


            val intent = Intent(this, HighScoreActivity::class.java)
            startActivity(intent)

        }

    }
    private fun rotateStar(){
        val rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)

        val rotImage = binding.rotatingShuriken

        rotImage.animation = rotate
    }
    private fun generateBackground(){

        val random = (0..6).random()

        val imgId = arrayOf(R.drawable.backgroundoneblur, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4,
            R.drawable.bg5, R.drawable.bg6, R.drawable.bg7)

        binding.layoutMain.setBackgroundResource(imgId[random])

    }

}