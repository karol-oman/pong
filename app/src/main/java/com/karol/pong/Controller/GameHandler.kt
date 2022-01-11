package com.karol.pong.Controller

import com.karol.pong.Model.Bricks

object GameHandler {


    val allBricks = mutableListOf<Bricks>()

    var paintArray = ArrayList<String>()

    /**
     * . = no brick
     * D = dragon fruit
     * G = green apple
     * K = kiwi
     * P = purple apple
     * S = strawberry
     * W = watermelon
     *
     * EACH ROW HAS TO BE 12 IN LENGTH.
     * ANY SIZE VERTICALLY.
     */

    fun original(){
        paintArray.add("WWWWWWWWWW")
        paintArray.add("WWWWWWWWWW")
        paintArray.add("SSSSSSSSSS")
        paintArray.add("SSSSSSSSSS")
        paintArray.add("DDDDDDDDDD")
        paintArray.add("DDDDDDDDDD")
        paintArray.add("KKKKKKKKKK")
        paintArray.add("KKKKKKKKKK")
        paintArray.add("GGGGGGGGGG")
        paintArray.add("GGGGGGGGGG")
    }


    fun japan(){
        paintArray.add("DDDDDDDDDD")
        paintArray.add("DDDD..DDDD")
        paintArray.add("DDDDSSDDDD")
        paintArray.add("DDDSSSSDDD")
        paintArray.add("D.SSSSSS.D")
        paintArray.add("D.SSSSSS.D")
        paintArray.add("D.SSSSSS.D")
        paintArray.add("DD.SSSS.DD")
        paintArray.add("DD.DSSD.DD")
        paintArray.add("DD.DDDD.DD")
        paintArray.add("DD.DDDD.DD")

    }

    fun ball(){
        paintArray.add("............")
        paintArray.add("............")
        paintArray.add(".....WW.....")
        paintArray.add("....WWWW....")
        paintArray.add("...WWWWWW...")
        paintArray.add("...WWWWWW...")
        paintArray.add("...WWWWWW...")
        paintArray.add("....WWWW....")
        paintArray.add(".....WW.....")
        paintArray.add("............")
        paintArray.add("............")

    }

    fun sarahLevel(){
        paintArray.add(".SSS.GGG.W")
        paintArray.add(".SSS.GGG.W")
        paintArray.add(".SSS.GGG.W")
        paintArray.add("...PPP.KKK")
        paintArray.add("...PPP.KKK")
        paintArray.add("...PPP.KKK")
        paintArray.add("WWW..GGG.S")
        paintArray.add("WWW..GGG.S")
        paintArray.add("WWW..GGG.S")
        paintArray.add("..PPP..KKK")
        paintArray.add("..PPP..KKK")
        paintArray.add("..PPP..KKK")
        paintArray.add("DDD..GGG.W")
        paintArray.add("DDD..GGG.W")



    }

    /**
     * . = no brick
     * D = dragon fruit
     * G = green apple
     * K = kiwi
     * P = purple apple
     * S = strawberry
     * W = watermelon
     *
     * EACH ROW HAS TO BE 12 IN LENGTH.
     * ANY SIZE VERTICALLY.
     */
}