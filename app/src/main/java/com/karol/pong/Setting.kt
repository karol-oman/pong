package com.karol.pong

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.graphics.scale

object Setting{


    //ID for drawables
    var ballID: Int = 0
    var paddleID = 0




    //Game modes
    //0 == Pong
    //1 == Bricks
    var gameMode = 0

    var level = 1

    var score = 0
    var test = 0

    var rageQuit = false


    //Ball speeds
    //Coming soon
    var speedY = 30f
    var speedX = 0f
    var totSpeed = speedX + speedY








}