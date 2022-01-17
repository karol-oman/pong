package com.karol.pong.model


import android.graphics.*


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