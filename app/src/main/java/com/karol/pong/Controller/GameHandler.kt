package com.karol.pong.Controller

import com.karol.pong.Model.Bricks

object GameHandler {


    val allBricks = mutableListOf<Bricks>()

    var paintArray = ArrayList<String>()

    /**
     * . = dragon fruit
     * G = green apple
     * K = kiwi
     * P = purple apple
     * S = strawberry
     * W = watermelon
     */

    fun original(){
        paintArray.add("WWWWWWWWWWWW")
        paintArray.add("WWWWWWWWWWWW")
        paintArray.add("SSSSSSSSSSSS")
        paintArray.add("SSSSSSSSSSSS")
        paintArray.add("............")
        paintArray.add("............")
        paintArray.add("KKKKKKKKKKKK")
        paintArray.add("KKKKKKKKKKKK")
        paintArray.add("GGGGGGGGGGGG")
        paintArray.add("GGGGGGGGGGGG")
    }


    fun japan(){
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

    fun fruitSalad(){
        paintArray.add(".....SSSSSSS")
        paintArray.add(".....SSSSSSS")
        paintArray.add(".....SSSSSSS")
        paintArray.add("....GGGSSSSS")
        paintArray.add("GGGGGGWW....")
        paintArray.add("GGGGGWWWWW..")
        paintArray.add("GGGGWWWWWWW.")
        paintArray.add("KKKKWWWWWW..")
        paintArray.add("KKKKKWWWWPPP")
        paintArray.add("KKKKKKPPPPPP")
        paintArray.add("KKKKKPPPPPPP")
    }
}