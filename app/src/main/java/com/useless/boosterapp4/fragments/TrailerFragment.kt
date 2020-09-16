package com.useless.boosterapp4.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.codesgood.views.JustifiedTextView
import com.useless.boosterapp4.R
import com.useless.boosterapp4.ui.MovieDetails


class TrailerFragment : Fragment() , MovieDetails.PassData{
    private lateinit var trailer : VideoView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_trailer, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    trailer = view.findViewById<VideoView>(R.id.trailer)
    }
    override fun iPassData(data: String?) {
        if (data != null) {
            trailer.setVideoPath(data)
        }
        else Toast.makeText(context, "No", Toast.LENGTH_SHORT).show()
    }
}
