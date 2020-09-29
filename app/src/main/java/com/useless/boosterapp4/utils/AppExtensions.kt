package com.useless.boosterapp4.utils

import com.useless.boosterapp4.data.MoviesDatabase.MovieMapper
import com.useless.boosterapp4.data.models.local.Movie
import com.useless.boosterapp4.data.models.remote.MovieResponse
import com.useless.boosterapp4.data.repository.LocalRepo

enum class MovieType{
    POP, RAT, FAV, UNFAV
}

fun MovieResponse.mapToMovieUi(page : Int, totalPages : Int): Movie {
    val mapper by lazy { MovieMapper() }
    return mapper.mapToMovieUi(this, page, totalPages)
}

fun Movie.flagAs(type: MovieType){

    when (type){
        MovieType.POP -> {
            this.isPop = true
            LocalRepo.insertMovie(this)
        }
        MovieType.RAT -> {
            this.isRat = true
            LocalRepo.insertMovie(this)
        }
        MovieType.FAV -> {
            this.fav = true
            LocalRepo.insertMovie(this)
        }
        MovieType.UNFAV ->{
            this.fav = false
            LocalRepo.insertMovie(this)
        }
    }
}
