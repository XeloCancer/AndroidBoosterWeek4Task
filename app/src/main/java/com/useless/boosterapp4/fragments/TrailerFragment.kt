package com.useless.boosterapp4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.useless.boosterapp4.R
import com.useless.boosterapp4.ui.MovieDetails


class TrailerFragment : Fragment() , MovieDetails.PassData, YouTubePlayer.OnInitializedListener{
    private lateinit var trailer : FrameLayout
    private val apiKey = "AIzaSyDsVxqRYXU2KrpxHvMUQ1uZjVPT2vPp6ac"
    private var videoLink : String? = null
    private val youtubePlayerFragment by lazy {
        YouTubePlayerSupportFragment()
    }
    private lateinit var player : YouTubePlayer
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_trailer, container, false)

        youtubePlayerFragment.initialize(apiKey, this)
        childFragmentManager.beginTransaction().apply {
            replace(R.id.youtube_player, youtubePlayerFragment)
            commit()
        }
        return view
    }

    override fun iPassData(data: String?) {
        if (data != null) {
            videoLink = data
        }
        else Toast.makeText(context, "No", Toast.LENGTH_SHORT).show()
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        if(!p2 && videoLink != null){
            p1!!.cueVideo(videoLink)
        }
        player = p1!!
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Toast.makeText(context, "No", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}
