package com.useless.boosterapp4.data.MoviesDatabase

import com.useless.boosterapp4.data.models.local.Movie
import com.useless.boosterapp4.data.models.remote.MovieListResponse
import com.useless.boosterapp4.data.models.remote.MovieResponse
import com.useless.boosterapp4.utils.mapToMovieUi


class MovieMapper {

    fun mapToMovieUi(movieResponse: MovieResponse, page: Int, totalPages: Int): Movie {
        return Movie(
            movieResponse.posterPath,
            movieResponse.Lang,
            movieResponse.movie_title,
            movieResponse.date,
            movieResponse.voteAvg.toDouble(),
            movieResponse.voteCnt,
            movieResponse.overview,
            page,
            totalPages,
            movieResponse.id
        )
    }

    fun mapToMovieListUi(movieListResponse: MovieListResponse): List<Movie> {
        return movieListResponse.list.map {
            it.mapToMovieUi(movieListResponse.page, movieListResponse.totalPages)
        }
    }
}