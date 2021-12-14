package com.karol.pong

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import java.io.File


object DataManager {

    private
    var path = "highscores.txt"
    private var file = File(path)



    fun save(scoreList : ArrayList<Score>) {


        if (!file.exists()){
            file.createNewFile()
        }
        File(path).bufferedWriter().use { out ->
            for (score: Score in scoreList) {
                out.write(score.name + "|" + score.score.toString())
                out.write("\n")
            }

            out.close()
        }
    }


    fun load() : ArrayList<Score>{

        var scoreboard = ArrayList<Score>()

        //Temporary for comparison
        scoreboard.add(Score("Person1", 10))
        scoreboard.add(Score("Person2", 50))
        scoreboard.add(Score("Person3", 100))
        scoreboard.add(Score("Person4", 150))



        if (!file.exists()){
            file.createNewFile()
        }
        val bufferedReader = File(path).bufferedReader()
        bufferedReader.useLines { lines -> lines.forEach {

            var scoreData = it.split("|")
            scoreboard.add(Score(scoreData[0], scoreData[1].toInt()))

        }
        }

        bufferedReader.close()
        return scoreboard
    }
}
