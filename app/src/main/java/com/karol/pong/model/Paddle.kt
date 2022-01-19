package com.karol.pong.model


import android.graphics.RectF

/**
 * Paddle class with all attributes for the game
 * and creates a lateinit for the paddle hitbox
 *  @authors Sarah, Gustav, Karol, Calle
 */

class Paddle(

    var posX: Float = 0f,
    var posY: Float = 0f,
    var width: Float = Setting.paddleWidth,
    var height: Float = Setting.paddleHeight,

    ) {

    lateinit var paddle: RectF

}