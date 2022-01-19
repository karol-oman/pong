package com.karol.pong.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.karol.pong.*
import kotlinx.android.synthetic.main.fragment_game_over.*
import kotlinx.android.synthetic.main.fragment_game_over.view.*
import android.graphics.Color
import com.karol.pong.controller.DataController
import com.karol.pong.model.Score
import com.karol.pong.model.Setting
import com.karol.pong.view.MainActivity
import com.karol.pong.view.PlayActivity

/**
 *  @authors Sarah, Gustav, Karol, Calle
 */

class GameOverFragment(context1: Context, private val gameMode: Int) : Fragment() {


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
        if (dataController.validateScore(Setting.score, Setting.gameMode)) {
            view.textInputLayout.visibility = View.VISIBLE
            view.edit_text_if_highscore.visibility = View.VISIBLE
        } else {
            view.textInputLayout.visibility = View.INVISIBLE
            view.edit_text_if_highscore.visibility = View.INVISIBLE
        }

        view.game_over_score.text = "Total score: " + Setting.score.toString()
        //Make warning pop up if u save without saving with a blank name
        view.button_main_menu.setOnClickListener {
            areYouSure(1)
        }

        view.button_restart.setOnClickListener {
            areYouSure(2)
        }

        return view
    }

    /**
     * Creates a new intent to PlayActivity so the user gets to play again
     * and sets the score to zero
     */
    private fun restart() {
        val intent = Intent(activity, PlayActivity::class.java)
        Setting.score = 0
        startActivity(intent)
    }

    /**
     * Creates a new intent to MainActivity and sets the score to zero
     */
    private fun goHome() {
        val intent = Intent(activity, MainActivity::class.java)
        Setting.score = 0
        startActivity(intent)
    }

    private fun save() {
        if (edit_text_if_highscore.text!!.isNotBlank()) {
            dataController.saveScore(
                Score(edit_text_if_highscore.text.toString(), Setting.score),
                gameMode
            )
            Toast.makeText(context, "Your score was successfully saved", Toast.LENGTH_SHORT).show()

        } else if (edit_text_if_highscore.text!!.isBlank()) Toast.makeText(
            context,
            "Your name cannot be empty",
            Toast.LENGTH_SHORT
        ).show()
    }


    /**
     * If the EditText is blank and the score is a top 10 score the user gets a prompt to make sure
     * the user doesn't want to save. Otherwise saves score and goes home/restarts
     */
    private fun areYouSure(where: Int) {

        if (edit_text_if_highscore.text!!.isBlank() && dataController.validateScore(Setting.score, Setting.gameMode)) {

            val builder = AlertDialog.Builder(context!!, R.style.ThemeOverlay_AppCompat_Dialog)
            builder.setMessage("Do you really want to continue without saving?")
                .setPositiveButton(
                    "Yes"
                ) { _, _ ->
                    when (where) {
                        1 -> {
                            Setting.score = 0
                            goHome()
                        }

                        2 -> {
                            Setting.score = 0
                            restart()
                        }
                    }
                }
                .setNegativeButton(
                    "No"
                ) { dialog, _ ->
                    dialog.dismiss()
                }
            val dialog = builder.create()

            dialog.show()
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
        } else {
            when (where) {
                1 -> {
                    if (edit_text_if_highscore.text!!.isNotBlank()) save()
                    Setting.score = 0
                    goHome()
                }
                2 -> {
                    if (edit_text_if_highscore.text!!.isNotBlank()) save()
                    Setting.score = 0
                    restart()
                }
            }
        }

    }

}