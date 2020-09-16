package com.useless.boosterapp4.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.codesgood.views.JustifiedTextView
import com.useless.boosterapp4.MovieDetails
import com.useless.boosterapp4.R

class TrailerFragment : Fragment() {
    private lateinit var trailer : VideoView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_trailer, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trailer = view.findViewById<VideoView>(R.id.trailer)

    }
}