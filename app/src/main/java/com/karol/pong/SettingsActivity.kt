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
    private var ballSelected: Int = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val pagerAdapter = ViewPagerAdapter(this)
        val pagerAdapterTwo =ViewPagerAdapterTwo(this)
        binding.viewPagerBall.adapter = pagerAdapter
        binding.viewPagerBackground.adapter = pagerAdapterTwo

        button_save.setOnClickListener() {

            //println("current ID:" + binding.viewPagerBall.currentItem)
            println("Current BG:" + binding.viewPagerBackground.currentItem)


            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("ballID", binding.viewPagerBall.currentItem )
            println("BALL ID IN SETTINGS ${binding.viewPagerBall.currentItem}")
            //intent.putExtra("ballID", )
            startActivity(intent)


        }

    }

    private inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 4


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
                else -> {
                    BallOneFragment()
                }

            }
        }
    }

    private inner class ViewPagerAdapterTwo(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3

        //TODO Går det att lägga dom i samma Adapter?

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
                }else -> {
                    PaddleOneFragment()
                }
            }
        }
    }

}

