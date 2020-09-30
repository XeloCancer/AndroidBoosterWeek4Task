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
            if(LocalRepo.getMovieFromDBase(this.id) != null){
                var movie: Movie? = LocalRepo.getMovieFromDBase(this.id)
                movie!!.isPop = true
                LocalRepo.insertMovie(movie)
            }else{
                this.isPop = true
                LocalRepo.insertMovie(this)
            }
        }
        MovieType.RAT -> {
            if(LocalRepo.getMovieFromDBase(this.id) != null){
            var movie: Movie? = LocalRepo.getMovieFromDBase(this.id)
            movie!!.isRat = true
            LocalRepo.insertMovie(movie)
        }else{
            this.isRat = true
            LocalRepo.insertMovie(this)
        }
        }
        MovieType.FAV -> {
            var movie: Movie? = LocalRepo.getMovieFromDBase(this.id)
            movie!!.fav = true
            LocalRepo.insertMovie(movie)
        }
        MovieType.UNFAV ->{
            var movie: Movie? = LocalRepo.getMovieFromDBase(this.id)
            movie!!.fav = false
            LocalRepo.insertMovie(movie)
        }
    }
}
