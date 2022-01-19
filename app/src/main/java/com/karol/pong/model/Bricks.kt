package com.karol.pong.model


import android.graphics.*

/**
 * Brick class with all attributes for the game
 *  @authors Sarah, Gustav, Karol, Calle
 */

class Bricks(

    var posX: Float,
    var posY: Float,
    val paintedBrick: Bitmap,
    var brickScore: Int,
    val height: Float,
    var destroy: Boolean = false,

    var bricks: RectF = RectF(
        posX,
        posY,
        posX + Setting.brickWidth,
        posY + Setting.brickHeight
    )
)