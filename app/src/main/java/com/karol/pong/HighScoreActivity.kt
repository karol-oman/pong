package com.karol.pong

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.karol.pong.databinding.ActivityHighscoreBinding
import com.karol.pong.fragments.HighScorePongFragment
import com.karol.pong.fragments.HighScoreShurikenFragment

class HighScoreActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHighscoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHighscoreBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val pagerAdapter = ViewPagerAdapter(this)

        binding.pageViewerHighScore.adapter = pagerAdapter
    }

    private inner class ViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){
        override fun getItemCount(): Int = 2


        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    HighScorePongFragment()
                }
                1 -> {
                    HighScoreShurikenFragment()
                }
                else -> {
                    HighScorePongFragment()
                }

            }
        }

    }
}
