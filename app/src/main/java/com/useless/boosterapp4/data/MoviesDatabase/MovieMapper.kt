package com.useless.boosterapp4.data.MoviesDatabase

import com.useless.boosterapp4.data.models.local.Movie
import com.useless.boosterapp4.data.models.remote.MovieListResponse
import com.useless.boosterapp4.data.models.remote.MovieResponse
import com.useless.boosterapp4.utils.mapToMovieUi


class MovieMapper {

    fun mapToMovieUi(movieResponse: MovieResponse, page: Int, totalPages: Int): Movie {
        return Movie(
            id = movieResponse.id,
            title = movieResponse.movie_title,
            posterPath = movieResponse.posterPath,
            lang = movieResponse.Lang,
            date = movieResponse.date,
            voteAvg = movieResponse.voteAvg.toDouble(),
            voteCnt = movieResponse.voteCnt,
            overview = movieResponse.overview,
            popularity = movieResponse.popularity.toDouble(),
            page = page,
            totalPages = totalPages,
        )
    }

    fun mapToMovieListUi(movieListResponse: MovieListResponse): List<Movie> {
        return movieListResponse.list.map {
            it.mapToMovieUi(movieListResponse.page, movieListResponse.totalPages)
        }
    }
}