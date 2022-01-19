package com.karol.pong.controller

import com.karol.pong.model.Bricks

/**
 *  @authors Sarah, Gustav, Karol, Calle
 */

object GameHandler {

    val allBricks = mutableListOf<Bricks>()

    var paintArray = ArrayList<String>()

    /**
     * GameHandler holds a list of Bricks.
     * GameHandler also holds a list of Strings with Level Designs to be drawn by the DrawHandler.
     *
     *
     * . = no brick
     * D = dragon fruit
     * G = green apple
     * K = kiwi
     * P = purple apple
     * S = strawberry
     * W = watermelon
     *
     * EACH ROW HAS TO BE 10 IN LENGTH.
     * ANY SIZE VERTICALLY.
     */


    fun bill() {
        paintArray.add("DDD..S.D.D")
        paintArray.add("D..D...D.D")
        paintArray.add("DDD..D.D.D")
        paintArray.add("D..D.D.D.D")
        paintArray.add("DDD..D.D.D")
        paintArray.add("..........")
        paintArray.add("..S....S..")
        paintArray.add(".SSS..SSS.")
        paintArray.add("SSSSSSSSSS")
        paintArray.add(".SSSSSSSS.")
        paintArray.add("..SSSSSS..")
        paintArray.add("...SSSS...")
        paintArray.add("....SS....")

    }


    fun japan() {
        paintArray.add("DDDDDDDDDD")
        paintArray.add("DDDDDDDDDD")
        paintArray.add("DDDDSSDDDD")
        paintArray.add("DDDSSSSDDD")
        paintArray.add("D.SSSSSS.D")
        paintArray.add("D.SSSSSS.D")
        paintArray.add("D.SSSSSS.D")
        paintArray.add("D..SSSS..D")
        paintArray.add("DD.DSSD.DD")
        paintArray.add("DD.DDDD.DD")
        paintArray.add("DD.DDDD.DD")

    }

    fun ball() {
        paintArray.add("..........")
        paintArray.add("..........")
        paintArray.add("....WW....")
        paintArray.add("...WWWW...")
        paintArray.add("..WWWWWW..")
        paintArray.add("..WWWWWW..")
        paintArray.add("..WWWWWW..")
        paintArray.add("...WWWW...")
        paintArray.add("....WW....")
        paintArray.add("..........")
        paintArray.add("..........")

    }

    fun sarahLevel() {
        paintArray.add("..S...G...")
        paintArray.add(".SSS.GGG.W")
        paintArray.add("..S...G...")
        paintArray.add("D..P...K.")
        paintArray.add("DD.PPP.KKK")
        paintArray.add("D...P...K.")
        paintArray.add(".W....G...")
        paintArray.add("WWW..GGG.S")
        paintArray.add(".W....G...")
        paintArray.add("...P....K.")
        paintArray.add("..PPP..KKK")
        paintArray.add("...P....K.")
        paintArray.add(".D....G...")
        paintArray.add("DDD..GGG.W")

    }

    fun labyrinth() {
        paintArray.add("DDDDDDDDDD")
        paintArray.add("GGGGGGGGGG")
        paintArray.add("..........")
        paintArray.add("..........")
        paintArray.add("..DDDDDDDD")
        paintArray.add("..GGGGGGGG")
        paintArray.add("..........")
        paintArray.add("..........")
        paintArray.add("DDDDDDDD..")
        paintArray.add("GGGGGGGG..")
        paintArray.add("..........")
        paintArray.add("..........")
        paintArray.add("..DDDDDDDD")
        paintArray.add("..GGGGGGGG")
    }

    fun apple() {
        paintArray.add("....KK......")
        paintArray.add(".....K......")
        paintArray.add(".....WW.....")
        paintArray.add("...KWWWWK...")
        paintArray.add("..KWWWWWWK..")
        paintArray.add("..KWWWWWWK..")
        paintArray.add("..KWWWWWWK..")
        paintArray.add("..KWKWWKWK..")
        paintArray.add("..KWWWWWWK..")
        paintArray.add("..KWWWWWWK..")
        paintArray.add("..KWWWWWWK..")
        paintArray.add("...KWWWWK...")
        paintArray.add("....KWWK....")
        paintArray.add(".....KK.....")


    }

    fun karolLevel() {
        paintArray.add("DD......DD")
        paintArray.add(".GG....GG.")
        paintArray.add("..KK..KK..")
        paintArray.add("...PPPP...")
        paintArray.add("....PP....")
        paintArray.add("...PPPP...")
        paintArray.add("..KK..KK..")
        paintArray.add(".GG....GG.")
        paintArray.add("DD......DD")
    }
}