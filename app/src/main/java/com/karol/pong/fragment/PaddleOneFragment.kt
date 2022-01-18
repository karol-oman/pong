package com.karol.pong.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.karol.pong.R
import com.karol.pong.model.Setting
/**
 * Creates the fragment view and returns it
 */
class PaddleOneFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_paddel_one, container, false)
    }

}