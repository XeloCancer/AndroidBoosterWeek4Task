package com.useless.boosterapp4.recycler

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_recycler_item.view.*
import retrofit2.Retrofit

class MovieViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    val moviePicture : ImageView? = itemView.movie_poster
}