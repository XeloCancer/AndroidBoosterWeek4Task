package com.useless.boosterapp4.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.useless.boosterapp4.network.Movie
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.useless.boosterapp4.R

class RecyclerAdapter (private val listOfMovies: List<Movie>): RecyclerView.Adapter<MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieListView = layoutInflater.inflate(R.layout.movie_recycler_item, parent, false)
        return MovieViewHolder(movieListView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listOfMovies[position]
        Picasso.get().load("https://image.tmdb.org/t/p/w500/${movie.posterPath}").into(holder.moviePicture)
    }

    override fun getItemCount(): Int {
        return listOfMovies.size
    }
}

