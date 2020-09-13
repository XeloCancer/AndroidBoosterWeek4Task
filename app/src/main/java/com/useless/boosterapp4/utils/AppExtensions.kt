package com.useless.boosterapp4.utils

import android.view.View
import com.useless.boosterapp4.data.MoviesDatabase.MovieMapper
import com.useless.boosterapp4.data.models.local.Movie
import com.useless.boosterapp4.data.models.remote.MovieResponse

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

fun MovieResponse.mapToMovieUi(): Movie {
    val mapper by lazy { MovieMapper() }
    return mapper.mapToMovieUi(this)
}