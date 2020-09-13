package com.useless.boosterapp4.data.MoviesDatabase

import com.useless.boosterapp4.data.models.local.Movie
import com.useless.boosterapp4.data.models.remote.MovieResponse


class MovieMapper {

    fun mapToMovieUi(movieResponse: MovieResponse): Movie {
        return Movie(
                movieResponse.posterPath,
                movieResponse.Lang,
                movieResponse.movie_title,
                movieResponse.date,
                movieResponse.voteAvg,
                movieResponse.voteCnt,
                movieResponse.overview,
                movieResponse.id
            )
    }
}