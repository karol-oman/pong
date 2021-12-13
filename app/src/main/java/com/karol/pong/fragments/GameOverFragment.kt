package com.karol.pong.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.karol.pong.MainActivity
import com.karol.pong.PlayActivity
import com.karol.pong.R
import kotlinx.android.synthetic.main.fragment_game_over.view.*

class GameOverFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        //return inflater.inflate(R.layout.fragment_game_over, container, false)

        val view: View = inflater.inflate(R.layout.fragment_game_over, container, false)

        view.button_main_menu.setOnClickListener{
            println("Main menu")
            val intent = Intent(activity, MainActivity::class.java)

                //Intent(this, PlayActivity::class.java)
            startActivity(intent)
        }
        view.button_restart.setOnClickListener{
            println("Restart")
            val intent = Intent(activity, PlayActivity::class.java)

            startActivity(intent)
        }


        return view
    }


}