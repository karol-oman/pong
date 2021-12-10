package com.karol.pong

object DataController {

    fun validateScore(score : Score) : Boolean{

        return checkIfTopTen(score)

    }

    fun saveScore(score : Score){

        setScore(score)

    }

    fun highestScore() : Score{

        return getHighestScore()

    }

    private fun getHighestScore() : Score{

        val scoreboard = DataManager.load()

        return scoreboard[0]

    }

    private fun setScore(newScore : Score) {


        var scoreboard = DataManager.load()

        scoreboard.add(newScore)

        scoreboard.sortBy { Score -> Score.score }

        if (scoreboard.size >= 10){
            scoreboard.removeAt(10)
        }

    }

    private fun checkIfTopTen(scoreToCheck : Score) : Boolean{

        var scoreboard = DataManager.load()

        for (score : Score in scoreboard){
            if (score.score < scoreToCheck.score){
                return true

            }
        }

        return false

    }
}