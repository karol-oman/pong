package com.karol.pong

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View.inflate
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.navigation.NavigationView

import com.karol.pong.databinding.ActivityMainBinding
import com.karol.pong.databinding.ActivityMainBinding.inflate


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPlay.setOnClickListener(){
            val intent = Intent(this,PlayActivity::class.java)
            startActivity(intent)
        }

        binding.buttonSettings.setOnClickListener(){
            //TODO INTENT HERE asd
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.buttonScores.setOnClickListener(){



            val intent = Intent(this, HighScoreActivity::class.java)
            startActivity(intent)







            //window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))








        }

    }





}