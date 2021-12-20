package com.karol.pong

import android.content.Context

/**
 * The DataController handles all the communication with the DataHandler
 */

class DataController(val context : Context) {

    /**
     * Getters and setters for the various functions
     */

    fun validateScore(score : Int, gameMode : Int) : Boolean{

        return checkIfTopTen(score, gameMode)
    }

    fun saveScore(score : Score, gameMode : Int){
        setScore(score, gameMode)
    }

    fun highestScore(gameMode : Int) : Score{

        return getHighestScore(gameMode)
    }

    fun highscores(gameMode : Int) : ArrayList<Score>{

        return getHighScores(gameMode)
    }

    /**
     * Loads the highscores from DataManager and checks if the user has a top 10 score.
     * Returns true if it is, otherwise false (We decided to keep our scoreboard at 10 spots)
     * Also returns true if there's less than 10 spots filled in the scoreboard
     */

    private fun checkIfTopTen(scoreToCheck : Int, gameMode : Int) : Boolean{

        var scoreboard = DataManager.load(gameMode, context)

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
     * Loads the highscores and returns the highest Score if the highscores are not empty
     * Otherwise returns a temporary score of 0
     */

    private fun getHighestScore(gameMode : Int) : Score{


        val scoreboard = DataManager.load(gameMode, context)


        if (scoreboard.isNotEmpty()){
            scoreboard.sortByDescending { Score -> Score.score }

            return scoreboard[0]
        }

        else{

            scoreboard.add(Score("It's empty in here", 0))
            return scoreboard[0]
        }
    }

    /**
     * Loads the highscores and returns them all
     */

    private fun getHighScores(gameMode : Int) : ArrayList<Score>{

        return  DataManager.load(gameMode, context)
    }

    /**
     * When the player do get a top 10 score, the Score is added to the scoreboard and sorted ascending via the Score.score integers
     * If the scoreboard has more than 10 spots, we remove the last spot to maintain the scoreboard at 10 spots
     */

    private fun setScore(newScore : Score, gameMode : Int) {

        var scoreboard = DataManager.load(gameMode, context)
        scoreboard.add(newScore)
        scoreboard.sortByDescending { Score -> Score.score }

        if (scoreboard.size > 10){
            scoreboard.removeAt(10)
        }

        DataManager.save(scoreboard,gameMode, context)
    }
}