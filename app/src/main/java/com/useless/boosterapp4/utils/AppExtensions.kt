package com.useless.boosterapp4.utils

import com.useless.boosterapp4.data.MoviesDatabase.MovieMapper
import com.useless.boosterapp4.data.models.local.Movie
import com.useless.boosterapp4.data.models.remote.MovieResponse

fun MovieResponse.mapToMovieUi(page : Int, totalPages : Int): Movie {
    val mapper by lazy { MovieMapper() }
    return mapper.mapToMovieUi(this, page, totalPages)
}