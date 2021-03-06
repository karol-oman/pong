package com.karol.pong.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.karol.pong.model.Setting
import com.karol.pong.databinding.ActivitySettingsBinding
import com.karol.pong.fragment.*
import kotlinx.android.synthetic.main.activity_settings.*

/**
 *  @authors Sarah, Gustav, Karol, Calle
 */

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pagerAdapterOne = ViewPagerAdapter(this)
        val pagerAdapterTwo = ViewPagerAdapterTwo(this)
        binding.viewPagerBall.adapter = pagerAdapterOne
        binding.viewPagerPaddle.adapter = pagerAdapterTwo

        binding.viewPagerBall.setCurrentItem(Setting.ballID, false)
        binding.viewPagerPaddle.setCurrentItem(Setting.paddleID, false)


        /**
         * Saves the correct ID from current fragment so correct items are shown in GameView
         */
        button_save.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            Setting.ballID = binding.viewPagerBall.currentItem
            Setting.paddleID = binding.viewPagerPaddle.currentItem

            startActivity(intent)

        }

        /**
         * OnClickListeners for the right and left buttons below the fragments
         */
        binding.arrowLeftBall.setOnClickListener {
            if (binding.viewPagerBall.currentItem != 0) binding.viewPagerBall.currentItem =
                binding.viewPagerBall.currentItem - 1
        }

        binding.arrowRightBall.setOnClickListener {
            if (binding.viewPagerBall.currentItem != 4) binding.viewPagerBall.currentItem =
                binding.viewPagerBall.currentItem + 1
        }

        binding.arrowLeftPaddle.setOnClickListener {
            if (binding.viewPagerPaddle.currentItem != 0) binding.viewPagerPaddle.currentItem =
                binding.viewPagerPaddle.currentItem - 1
        }

        binding.arrowRightPaddle.setOnClickListener {
            if (binding.viewPagerPaddle.currentItem != 2) binding.viewPagerPaddle.currentItem =
                binding.viewPagerPaddle.currentItem + 1
        }

    }


    /**
     * ViewPager for the different balls
     */
    private inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 5


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

    /**
     * ViewPager for the different paddles
     */
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

