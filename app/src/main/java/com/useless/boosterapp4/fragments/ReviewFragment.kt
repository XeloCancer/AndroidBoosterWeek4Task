package com.useless.boosterapp4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.codesgood.views.JustifiedTextView
import com.useless.boosterapp4.ui.MovieDetails
import com.useless.boosterapp4.R

class ReviewFragment : Fragment() , MovieDetails.PassData {
    private lateinit var moveOver2 : JustifiedTextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_review,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moveOver2 = view.findViewById<JustifiedTextView>(R.id.reviewText)
    }
    override fun iPassData(data: String?) {
        if (data != null) {
            moveOver2.text = data
        }
        else Toast.makeText(context, "No", Toast.LENGTH_SHORT).show()
    }
}
