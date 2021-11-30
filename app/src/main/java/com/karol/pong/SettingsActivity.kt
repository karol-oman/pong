package com.karol.pong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.karol.pong.databinding.ActivityMainBinding
import com.karol.pong.databinding.ActivitySettingsBinding
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private var ballSelected: Int = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val pagerAdapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter

        button_save.setOnClickListener(){
            println(ballSelected)
            finish()
        }

    }




    private inner class ViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){
        override fun getItemCount(): Int = 2


        var currentPage: Int = 0

        //TODO FIX POSITION!!!
        fun onPageSelected(position: Int) {
            currentPage = position
            println(currentPage)
        }

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0  -> {
                    ballSelected = 0
                    onPageSelected(ballSelected)
                    BallOneFragment()

                }
                1 -> {
                    ballSelected = 1
                    onPageSelected(ballSelected)
                    BallTwoFragment()

                }else-> {
                    ballSelected = 0
                    BallOneFragment()
                }

            }
        }
    }







}
