package com.useless.boosterapp4.MoviesDatabase

import com.useless.boosterapp4.network.Movie
import com.useless.boosterapp4.remote.MovieResponse
import  com.useless.boosterapp4.remote.MovieData


class MovieMapper {

    fun mapToMovieUi(movieResponse: MovieResponse): List<Movie> {
        val movies = mutableListOf<Movie>()
        var title = ""
        var overview = ""
      movieResponse.movielist.forEach(){
          if (it.movie_info.isNotEmpty()) {
          title = it.movie_info.first().movie_title
          overview = it.movie_info.first().overview
      }
          movies.add(Movie(it.posterPath, it.Lang, title, it.date, it.voteAvg, it.voteCnt, overview, it.id))
      }
        return movies
        }

}