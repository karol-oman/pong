package com.karol.pong

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString


object DataManager {

    private var scoreboard = ArrayList<Score>() //Temporary


    fun save(scoreList : ArrayList<Score>){

        //TODO: Code for Saving scoreList to file or shared Preferences

        scoreboard = scoreList

    }


    fun load() : ArrayList<Score>{

        //TODO: Code for Loading scores from file or shared Preferences

        scoreboard.clear()
        for (score : Int in 1..10){

            var tempPerson = "Person$score"
            var tempScore = score

            scoreboard.add(Score(tempPerson, tempScore))
        }

        return scoreboard

    }

}