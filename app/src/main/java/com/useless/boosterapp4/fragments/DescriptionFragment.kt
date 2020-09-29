package com.useless.boosterapp4.fragments

import android.annotation.SuppressLint
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.codesgood.views.JustifiedTextView
import com.useless.boosterapp4.ui.MovieDetails
import com.useless.boosterapp4.R

class DescriptionFragment : Fragment() , MovieDetails.PassData{
private lateinit var moveOver : TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_description, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         moveOver = view.findViewById(R.id.movie_overview1)
           }
    override fun iPassData(data: String?) {
        if (data != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                @SuppressLint("InlinedApi")
                moveOver.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            }
            moveOver.text = data
        }
        else Toast.makeText(context, "No", Toast.LENGTH_SHORT).show()
    }
}