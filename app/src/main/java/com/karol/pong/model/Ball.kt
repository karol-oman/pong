package com.karol.pong.model


import android.graphics.*

/**
 * Ball class with all attributes for the game
 */
class Ball(

    var posX: Float,
    var posY: Float,
    var size: Float,
    var speedX: Float,
    var speedY: Float,
    var height: Float,
    var bounce: Boolean = false,

    var hitbox: RectF = RectF(

        posX - size,
        posY - size,
        posX + size,
        posY + size

    )
)