package com.useless.boosterapp4

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.useless.boosterapp4.network.Movie
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Retrofit

class RecyclerAdapter (private val listOfMovies: List<Movie>): RecyclerView.Adapter<MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val noteView = layoutInflater.inflate(R.layout.movie_recycler_item, parent, false)
        return MovieViewHolder(noteView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listOfMovies[position]
        holder.moviePicture?.setImageResource(movie.moviePicture)
    }

    override fun getItemCount(): Int {
        return listOfMovies.size
    }
}

