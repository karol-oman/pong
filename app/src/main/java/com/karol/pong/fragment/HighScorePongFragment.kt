package com.karol.pong.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.karol.pong.*
import com.karol.pong.controller.DataController
import com.karol.pong.model.Score
import kotlinx.android.synthetic.main.layout_high_score_pong.view.*
import kotlinx.android.synthetic.main.list_view_item.view.*

/**
 * Fragment for PageViewer for Pong in HighScoreActivity
 */

class HighScorePongFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.layout_high_score_pong, container, false)
        val dataController = DataController(activity!!.applicationContext)


        /**
         * Creates a custom adapter based on our own ListViewItem
         */

        val adapter = object : ArrayAdapter<Score>(
            activity!!.applicationContext,
            R.layout.list_view_item, R.id.list_item_name,
            dataController.highscores(0)
        ) {
            override fun getView(position: Int, concertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, concertView, parent)

                view.list_item_name.text = dataController.highscores(0)[position].name
                view.list_item_score.text = dataController.highscores(0)[position].score.toString()


                /**
                 * Top three scores gets a medal to the left of the name
                 */
                when (position) {
                    0 -> {
                        view.list_item_image.setImageResource(R.drawable.gold)
                    }
                    1 -> {
                        view.list_item_image.setImageResource(R.drawable.silver)
                    }
                    2 -> {
                        view.list_item_image.setImageResource(R.drawable.bronze)
                    }
                }

                return view
            }
        }
        view.list_view_pong.adapter = adapter
        return view

    }

}