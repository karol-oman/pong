package com.karol.pong.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.karol.pong.*
import kotlinx.android.synthetic.main.fragment_game_over.*
import kotlinx.android.synthetic.main.fragment_game_over.view.*

class GameOverFragment(context1: Context, score : Int): Fragment() {

    private var dataController = DataController(context1)
    val score = score;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {

        /**
         * GameOverFragment is displayed when a game is over
         * Binds the view and displays an editText depending on if the player gets a top 10 score
         * The editText lets the player enter a name and creates a score object
         * The user can now choose to Play again or Go back, and a Score-object is created and saved (if applicable)
         */

        val view: View = inflater.inflate(R.layout.fragment_game_over, container, false)
        if (dataController.validateScore(score)) view.edit_text_if_highscore.visibility = View.VISIBLE
        else view.edit_text_if_highscore.visibility = View.INVISIBLE

        view.button_main_menu.setOnClickListener{
            if (dataController.validateScore(score)) dataController.saveScore(Score(edit_text_if_highscore.text.toString(), score))
            println("Main menu with saved score")
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        view.button_restart.setOnClickListener{
            if (dataController.validateScore(score)) dataController.saveScore(Score(edit_text_if_highscore.text.toString(), score))
            println("Restart with saved score")
            val intent = Intent(activity, PlayActivity::class.java)
            //TODO FIX GAME MODE HERE
            startActivity(intent)
        }

        return view
    }

}