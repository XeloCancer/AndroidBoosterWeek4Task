package com.useless.boosterapp4.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.codesgood.views.JustifiedTextView
import com.useless.boosterapp4.MovieDetails
import com.useless.boosterapp4.R

class DescriptionFragment : Fragment() , MovieDetails.PassData{
lateinit var moveOver : JustifiedTextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_description, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         moveOver = view.findViewById<JustifiedTextView>(R.id.movie_overview1)
           }
    override fun IPassData(data: String?) {
        if (data != null) {

            moveOver.text = data
        }
        else Toast.makeText(context, "No", Toast.LENGTH_SHORT).show()
    }
}