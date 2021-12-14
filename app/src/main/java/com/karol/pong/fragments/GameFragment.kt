package com.karol.pong.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.karol.pong.GameView

class GameFragment(private val gameMode: Int, private val ballId: Int): Fragment() {


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View {

        return GameView(activity, gameMode, ballId)
    }
    }


