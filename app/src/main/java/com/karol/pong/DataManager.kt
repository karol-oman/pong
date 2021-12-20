package com.karol.pong

import android.content.Context
import java.io.File
import java.lang.Exception

/**
 * The DataManager is a static object responsible for all communication with text files (one txt for each gamemode)
 */

object DataManager {

    /**
     * The function "save" takes an ArrayList with Score-objects as an inparameter and writes them as raw String data to the corresponding highscore file
     * One Score-object per row, name and score separated with "|"
     */
    fun save(scoreList: ArrayList<Score>, gameMode: Int, context: Context) {

        var path = ""

        when(gameMode){
            0 -> path = "highscores_gamemode_0.txt"
            1 -> path = "highscores_gamemode_1.txt"
        }

        var file = File(context.filesDir,path)


        try {

            if (!file.exists()) {
                file.createNewFile()
            }
        } catch (e: Exception) {

            println(e)
        }

        try {

            File(file.path).bufferedWriter().use { out ->
                for (score: Score in scoreList) {
                    out.write(score.name + "|" + score.score.toString())
                    out.write("\n")
                }

                out.close()
            }
        } catch (e: Exception) {

            println(e)
        }

    }


    /**
     * The function "load" retrieves the raw-data from the corresponding highscore file and reads them line by line and splits them by the "|" character
     * Makes an Score-object that gets added to the scoreboard ArrayList which is then returned
     */
    fun load(gameMode: Int, context: Context): ArrayList<Score> {

        var path = ""

        when(gameMode){
            0 -> path = "highscores_gamemode_0.txt"
            1 -> path = "highscores_gamemode_1.txt"
        }

        var file = File(context.filesDir,path)

        val scoreboard = ArrayList<Score>()
        try {

            if (!file.exists()) {
                file.createNewFile()
            }
        } catch (e: Exception) {

            println(e)
        }

        try {

            val bufferedReader = File(file.path).bufferedReader()
            bufferedReader.useLines { lines ->
                lines.forEach {

                    val scoreData = it.split("|")
                    scoreboard.add(Score(scoreData[0], scoreData[1].toInt()))

                }
            }
            bufferedReader.close()
        } catch (e: Exception) {

            println(e)
        }

        return scoreboard
    }
}