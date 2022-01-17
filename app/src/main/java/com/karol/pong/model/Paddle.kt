package com.karol.pong.model


import android.graphics.RectF


class Paddle(

    var posX: Float = 0f,
    var posY: Float = 0f,
    var width: Float = Setting.paddleWidth,
    var height: Float = Setting.paddleHeight,

    ) {

    lateinit var paddle: RectF

}