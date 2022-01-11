package com.karol.pong.Model

import android.content.res.Resources

object Setting{

    val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    val screenHeight = Resources.getSystem().displayMetrics.heightPixels

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

    var speedMultiplier = 1.4f

    val brickHeight = 70f

    val paddleHeight = 50f
    var paddleWidth : Float = screenWidth / 2.5f


    val margin = (screenWidth / 12 * 2) / 10
    val brickWidth = screenWidth / 12





}