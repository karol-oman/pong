package com.karol.pong.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.karol.pong.GameView

class GameFragment(gameMode: Int): Fragment() {
    val game: Int = gameMode

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View? {

        return GameView(activity, game)
    }
    }


