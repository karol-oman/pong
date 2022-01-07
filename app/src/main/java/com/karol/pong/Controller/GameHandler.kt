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
        paintArray.add("WWWWWWWWWWWW")
        paintArray.add("WWWWWWWWWWWW")
        paintArray.add("SSSSSSSSSSSS")
        paintArray.add("SSSSSSSSSSSS")
        paintArray.add("DDDDDDDDDDDD")
        paintArray.add("DDDDDDDDDDDD")
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
        paintArray.add("DDDDDSSSSSSS")
        paintArray.add("DDDDDSSSSSSS")
        paintArray.add("DDDDDSSSSSSS")
        paintArray.add("DDDDGGGSSSSS")
        paintArray.add("GGGGGGWWDDDD")
        paintArray.add("GGGGGWWWWWDD")
        paintArray.add("GGGGWWWWWWWD")
        paintArray.add("KKKKWWWWWWDD")
        paintArray.add("KKKKKWWWWPPP")
        paintArray.add("KKKKKKPPPPPP")
        paintArray.add("KKKKKPPPPPPP")
    }
}