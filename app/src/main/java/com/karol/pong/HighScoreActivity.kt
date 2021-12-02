package com.karol.pong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.karol.pong.databinding.ActivityHighscoreBinding

class HighScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHighscoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHighscoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pagerAdapter = ViewPagerAdapter(this)

        binding.pageViewerHighScore.adapter = pagerAdapter



    }

    private inner class ViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){
        override fun getItemCount(): Int = 1


        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    HighScorePongFragment()
                }
                1 -> {
                    HighScorePongFragment()
                }
                else -> {
                    HighScorePongFragment()
                }

            }
        }

    }
}
