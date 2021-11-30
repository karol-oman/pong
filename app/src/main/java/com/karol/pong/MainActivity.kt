package com.karol.pong

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.commit
import com.karol.pong.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonPlay.setOnClickListener(){
            val intent = Intent(this,PlayActivity::class.java)
            startActivity(intent)
        }

        binding.buttonSettings.setOnClickListener(){
            //TODO FRAGMENT HERE
            supportFragmentManager.commit {
                add(R.id.frame_layout, SettingsFragment())
            }
        }

        binding.buttonScores.setOnClickListener(){
            //TODO FRAGMENT HERE
        }

    }
}