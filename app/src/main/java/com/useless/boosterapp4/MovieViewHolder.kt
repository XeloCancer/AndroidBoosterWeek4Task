package com.useless.boosterapp4

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_recycler_item.view.*

class MovieViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    val moviePicture : ImageView? = itemView.movie_poster
    val movieTitle : TextView = itemView.movie_title
    val rating : TextView = itemView.rating
    val ratingBar : ProgressBar = itemView.rating_progress_bar
}