package com.useless.boosterapp4.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.core.text.HtmlCompat
import com.squareup.picasso.Picasso
import com.useless.boosterapp4.R
import kotlinx.android.synthetic.main.movie_details.*
import java.util.*

class MovieDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)

        val posterPath : String? = intent.extras?.getString("poster_path")
        val title : String? = intent.extras?.getString("title")
        val releaseDate : String? = intent.extras?.getString("release_date")
        val language : String? = intent.extras?.getString("language")!!.toUpperCase(Locale.ROOT)
        val overview : String? = intent.extras?.getString("overview")

        Picasso.get().load("https://image.tmdb.org/t/p/w300/${posterPath}").into(movie_poster_detail)
        movie_name.text = HtmlCompat.fromHtml("<b>Title:</b> $title", HtmlCompat.FROM_HTML_MODE_LEGACY)
        release_date.text = HtmlCompat.fromHtml("<b>Release Date:</b> $releaseDate", HtmlCompat.FROM_HTML_MODE_LEGACY)
        languages.text = HtmlCompat.fromHtml("<b>Languages:</b> ${language!!}", HtmlCompat.FROM_HTML_MODE_LEGACY)
        movie_overview.text = HtmlCompat.fromHtml(overview!!, HtmlCompat.FROM_HTML_MODE_LEGACY)

        back_button.setOnClickListener { finish() }
    }
}