package com.karol.pong.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.karol.pong.*
import kotlinx.android.synthetic.main.fragment_game_over.*
import kotlinx.android.synthetic.main.fragment_game_over.view.*

class GameOverFragment(context1: Context, val score: Int, val gameMode : Int): Fragment() {

    private var dataController = DataController(context1)
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
            when(dataController.validateScore(score)){

                true -> {
                    if (edit_text_if_highscore.text.isNotBlank()) { dataController.saveScore(Score(edit_text_if_highscore.text.toString(), score)); goHome() }
                    else if (edit_text_if_highscore.text.isBlank()) {Toast.makeText(context, "Your name cannot be empty", Toast.LENGTH_SHORT).show()}
                }

                false -> {
                    goHome()
                }
            }
        }

        view.button_restart.setOnClickListener{
            when(dataController.validateScore(score)){

                true -> {
                    if (edit_text_if_highscore.text.isNotBlank()) { dataController.saveScore(Score(edit_text_if_highscore.text.toString(), score)); restart() }
                    else if (edit_text_if_highscore.text.isBlank()) {Toast.makeText(context, "Your name cannot be empty", Toast.LENGTH_SHORT).show()}
                }

                false -> {
                    restart()
                }
            }
        }

        return view
    }

    private fun restart(){
        val intent = Intent(activity, PlayActivity::class.java)
        //TODO FIX GAME MODE HERE
        intent.putExtra("gamemode",gameMode)
        startActivity(intent)
    }

    private fun goHome(){
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }

}