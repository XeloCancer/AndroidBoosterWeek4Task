package com.useless.boosterapp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_details.*

class MovieDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)

        val posterPath : String? = intent.extras?.getString("poster_path")
        val title : String? = intent.extras?.getString("title")
        val releaseDate : String? = intent.extras?.getString("release_date")
        val language : String? = intent.extras?.getString("language")
        val overview : String? = intent.extras?.getString("overview")

        Picasso.get().load("https://image.tmdb.org/t/p/w500/${posterPath}").into(movie_poster_detail)
        movie_name.text = Html.fromHtml("<b>Title:</b> $title")
        release_date.text = Html.fromHtml("<b>Release Date:</b> $releaseDate")
        languages.text = Html.fromHtml("<b>Languages:</b> ${language!!.toUpperCase()}")
        movie_overview.text = Html.fromHtml(overview)

        back_button.setOnClickListener { finish() }
    }
}