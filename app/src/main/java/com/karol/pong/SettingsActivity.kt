package com.karol.pong

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.karol.pong.databinding.ActivitySettingsBinding
import com.karol.pong.fragments.*
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val pagerAdapterOne = ViewPagerAdapter(this)
        val pagerAdapterTwo =ViewPagerAdapterTwo(this)
        binding.viewPagerBall.adapter = pagerAdapterOne
        binding.viewPagerPaddle.adapter = pagerAdapterTwo


        button_save.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            Setting.ballID = binding.viewPagerBall.currentItem
            Setting.paddleID= binding.viewPagerPaddle.currentItem

            startActivity(intent)

        }

    }

    private inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 5


        //TODO FIX POSITION!!!

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    BallOneFragment()
                }
                1 -> {
                    BallTwoFragment()
                }
                2 -> {
                    BallThreeFragment()
                }
                3 -> {
                    BallFourFragment()
                }
                4 -> {
                    BallFiveFragment()
                }
                else -> {
                    BallOneFragment()
                }

            }
        }
    }

    private inner class ViewPagerAdapterTwo(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    PaddleOneFragment()
                }
                1 -> {
                    PaddleTwoFragment()
                }
                2 -> {
                    PaddleThreeFragment()
                }
                else -> {
                    PaddleOneFragment()
                }
            }
        }
    }

}

