package com.useless.boosterapp4

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.useless.boosterapp4.dataModels.local.Movie
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.useless.boosterapp4.dataModels.remote.MovieListResponse

class RecyclerAdapter (private val movieListData: MovieListResponse?, private val listOfMovies: List<Movie>, private val responseInterface: PageControl): RecyclerView.Adapter<MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent. context)
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

        //Creates a bundle of data to pass to the activity
        val bundle : Bundle = Bundle()
        bundle.putString("poster_path", movie.posterPath)
        bundle.putString("title", movie.title)
        bundle.putString("release_date", movie.date)
        bundle.putString("language", movie.lang)
        bundle.putString("overview", movie.overview)

        //OnClickListener responsible for sending data to the other activity
        holder.itemView.setOnClickListener {
            val intent : Intent = Intent(holder.itemView.context, MovieDetails::class.java)
            intent.putExtras(bundle)
            holder.itemView.context.startActivity(intent)
        }

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
        fun nextPage(movieListData: MovieListResponse)
    }
}

