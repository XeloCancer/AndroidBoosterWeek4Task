package com.useless.boosterapp4.MoviesDatabase

import com.useless.boosterapp4.network.Movie
import com.useless.boosterapp4.remote.MovieResponse
import  com.useless.boosterapp4.remote.MovieData


class MovieMapper {

    fun mapToMovieUi(movieResponse: MovieResponse): Movie {
        var title = ""
        var overview = ""
        if (movieResponse.movielist.isNotEmpty()) {
            overview = movieResponse.movielist.first().overview
            title = movieResponse.movielist.first().movie_title
        }
        return Movie(
            movieResponse.movieData.posterPath, movieResponse.movieData.Lang,
            movieResponse.movie_title, movieResponse.movieData.date,
            movieResponse.movieData.voteAvg, movieResponse.movieData.voteCnt,
            overview,
        )
    }

}