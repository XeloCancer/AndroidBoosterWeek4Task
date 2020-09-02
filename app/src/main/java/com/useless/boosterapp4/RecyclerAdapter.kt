package com.useless.boosterapp4

import android.view.LayoutInflater
import android.view.ViewGroup
import com.useless.boosterapp4.network.Movie
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.useless.boosterapp4.MovieViewHolder
import com.useless.boosterapp4.network.MovieList

class RecyclerAdapter (private val movieListData: MovieList?, private val listOfMovies: List<Movie>?, private val responseInterface: PageControl): RecyclerView.Adapter<MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieListView = layoutInflater.inflate(R.layout.movie_recycler_item, parent, false)
        return MovieViewHolder(movieListView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listOfMovies?.get(position)
        Picasso.get().load("https://image.tmdb.org/t/p/w500/${movie?.posterPath}").into(holder.moviePicture)
        holder.movieTitle.text = movie!!.title
        holder.rating.text = "${(movie.voteAvg.toDouble() * 10).toInt()}%"
        holder.ratingBar.progress = (movie.voteAvg.toDouble() * 10).toInt()
        System.out.println("Position is $position, and Item count is $itemCount")
        if(position == itemCount - 1){

            if (movieListData != null) {
                responseInterface.nextPage(movieListData)
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfMovies?.size ?: 0
    }

    interface PageControl{
        fun nextPage(movieListData: MovieList)
    }
}

