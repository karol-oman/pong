package com.karol.pong.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.karol.pong.Controller.GameView

class GameFragment(): Fragment() {


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View {

        return GameView(activity)
    }
    }


