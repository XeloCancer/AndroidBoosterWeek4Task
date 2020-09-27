package com.useless.boosterapp4.data.recyclerData

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.useless.boosterapp4.data.models.local.Movie
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.useless.boosterapp4.ui.MovieDetails
import com.useless.boosterapp4.R
import com.useless.boosterapp4.data.models.remote.MovieVideos
import com.useless.boosterapp4.data.models.remote.Video
import com.useless.boosterapp4.data.repository.LocalRepo
import com.useless.boosterapp4.ui.MovieViewModel

class RecyclerAdapter (private val listOfMovies: ArrayList<Movie>
): RecyclerView.Adapter<MovieViewHolder>(), MovieViewModel.PageControl, LocalRepo.MovieVideosCallback{
    val bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent. context)
        val movieListView = layoutInflater.inflate(R.layout.movie_recycler_item, parent, false)
        return MovieViewHolder(movieListView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listOfMovies[position]

        val movieRatingPercent = "${(movie.voteAvg * 10).toInt()}%"
        Picasso.get().load("https://image.tmdb.org/t/p/w300/${movie.posterPath}").into(holder.moviePicture)
        holder.movieTitle.text = movie.title
        holder.rating.text = movieRatingPercent
        holder.ratingBar.progress = (movie.voteAvg * 10).toInt()
        println("Position is $position, and Item count is $itemCount")

        //Creates a bundle of data to pass to the activity

        bundle.clear()
        bundle.putBoolean("fav", movie.fav)
        bundle.putString("poster_path", movie.posterPath)
        bundle.putString("title", movie.title)
        bundle.putString("release_date", movie.date)
        bundle.putString("language", movie.lang)
        bundle.putString("overview", movie.overview)

        //OnClickListener responsible for sending data to the other activity
        holder.itemView.setOnClickListener {
            LocalRepo.requestMovieVideos(this@RecyclerAdapter, movie.id, holder.itemView)
        }
    }

    override fun getItemCount(): Int {
        return listOfMovies.size ?: 0
    }

    private fun addData(newMovieList: List<Movie>){
        listOfMovies.addAll(newMovieList)
        notifyDataSetChanged()
    }

    override fun nextPage(movieList: List<Movie>) {
        addData(movieList)
    }

    override fun onMovieVideosReady(videoData: MovieVideos, itemView: View) {
        val intent = Intent(itemView.context, MovieDetails::class.java)
        bundle.putString("videoLink", "https://www.${videoData.data.first().site}.com/watch?v=${videoData.data.first().key}")
        intent.putExtras(bundle)
        itemView.context.startActivity(intent)
    }

    override fun onMovieVideosError(errorMsg: String) {
        TODO("Not yet implemented")
    }

}
