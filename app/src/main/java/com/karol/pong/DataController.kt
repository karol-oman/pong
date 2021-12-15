package com.karol.pong

import android.content.Context

/**
 * The DataController handles all the communication with the DataHandler
 */

class DataController(appContext : Context) {

    val context = appContext

    /**
     * Getters and setters for the various functions
     */

    fun validateScore(score : Int) : Boolean{

        return checkIfTopTen(score)
    }

    fun saveScore(score : Score){
        setScore(score)
    }

    fun highestScore() : Score{

        return getHighestScore()
    }

    fun highscores() : ArrayList<Score>{

        return getHighScores()
    }

    /**
     * Loads the highscores from DataManager and checks if the user has a top 10 score.
     * Returns true if it is, otherwise false (We decided to keep our scoreboard at 10 spots)
     * Also returns true if there's less than 10 spots filled in the scoreboard
     */

    private fun checkIfTopTen(scoreToCheck : Int) : Boolean{

        var scoreboard = DataManager.load(context)

        if (scoreboard.size < 10){
            return true
        }
        else{
            for (score : Score in scoreboard){
                if (score.score < scoreToCheck){

                    return true
                }
            }
        }

        return false
    }

    /**
     * Loads the highscores and returns the highest Score
     */

    private fun getHighestScore() : Score{

        val scoreboard = DataManager.load(context)
        scoreboard.sortByDescending { Score -> Score.score }

        return scoreboard[0]
    }

    /**
     * Loads the highscores and returns them all
     */

    private fun getHighScores() : ArrayList<Score>{

        return  DataManager.load(context)
    }

    /**
     * When the player do get a top 10 score, the Score is added to the scoreboard and sorted ascending via the Score.score integers
     * If the scoreboard has more than 10 spots, we remove the last spot to maintain the scoreboard at 10 spots
     */

    private fun setScore(newScore : Score) {

        var scoreboard = DataManager.load(context)
        scoreboard.add(newScore)
        scoreboard.sortByDescending { Score -> Score.score }

        if (scoreboard.size > 10){
            scoreboard.removeAt(10)
        }

        DataManager.save(scoreboard,context)
    }
}