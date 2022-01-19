package com.karol.pong.model

import android.content.res.Resources

/**
 * Singleton class for storing game variables
 *  @authors Sarah, Gustav, Karol, Calle
 */

object Setting {

    //Screen properties
    val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    //ID for drawables
    var ballID: Int = 0
    var paddleID = 0

    //Game modes
    //0 == Pong
    //1 == Bricks
    var gameMode = 0

    var score = 0

    //Bool to check if the built in back button
    //was pressed while the game is running
    var rageQuit = false

    //Ball speeds
    var ballSpeedY = -40f
    var ballSpeedX = 0f
    var speedMultiplier = 1.25f

    //Paddle properties
    val paddleHeight = 50f
    var paddleWidth: Float = screenWidth / 2.5f

    //Brick properties
    val brickHeight = 70f
    val brickWidth = screenWidth / 12

    val margin = (screenWidth / 12 * 2) / 10








}