package com.useless.boosterapp4.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.text.LineBreaker
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.codesgood.views.JustifiedTextView
import com.useless.boosterapp4.ui.MovieDetails
import com.useless.boosterapp4.R
import com.useless.boosterapp4.data.models.remote.ReviewData
import kotlinx.android.synthetic.main.fragment_review.*

class ReviewFragment : Fragment() , MovieDetails.PassReview {

    private var numOfReviews : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_review,container,false)
    }

    override fun iPassReview(data: ArrayList<ReviewData>?) {
        if (data!!.size > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                @SuppressLint("InlinedApi")
                review_details.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
            }
            review_author.text = HtmlCompat.fromHtml("<b>Author:</b> <br> ${data[numOfReviews].author}", HtmlCompat.FROM_HTML_MODE_LEGACY)
            review_details.text = data[numOfReviews].content
        }
        else{
            review_details.text = HtmlCompat.fromHtml("No reviews are available for this movie. You can visit themoviedb.org to add your review.", HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }
}
