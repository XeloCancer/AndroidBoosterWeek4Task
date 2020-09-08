package com.useless.boosterapp4

import android.view.LayoutInflater
<<<<<<< HEAD
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.useless.boosterapp4.network.Movie
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.squareup.picasso.Picasso
import com.useless.boosterapp4.MovieViewHolder
import com.useless.boosterapp4.R
import com.useless.boosterapp4.network.MovieDetails
import kotlinx.android.synthetic.main.movie_details.view.*
import kotlinx.android.synthetic.main.movie_recycler_item.view.*

class RecyclerAdapter (private val movies: List<Movie>?): RecyclerView.Adapter<MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieListView = layoutInflater.inflate(R.layout.movie_recycler_item, parent, false)
        return MovieViewHolder(movieListView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies?.get(position)
        Picasso.get().load("https://image.tmdb.org/t/p/w500/${movie?.posterPath}").into(holder.moviePicture)

    }

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }
    class RecyclerAdapter    (
        var movies: MutableList<Movie>,
        val onMovieClick: (movie: Movie) -> Unit) : RecyclerView.Adapter<RecyclerAdapter.MovieDetailsHolder>(){
        inner class MovieDetailsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val poster: ImageView = itemView.findViewById(R.id.movie_poster)
            fun bind(movie: Movie) {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                    .transform(CenterCrop())
                    .into(poster)
                itemView.setOnClickListener { onMovieClick.invoke(movie) }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDetailsHolder {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: MovieDetailsHolder, position: Int) {
            TODO("Not yet implemented")
        }


=======
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
>>>>>>> origin/Lotfy_Recycler_branch
    }
}

