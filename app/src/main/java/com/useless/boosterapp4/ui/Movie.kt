package com.useless.boosterapp4.ui

import android.widget.ImageView

data class Movie(
    val id: Int,
    val posterPath: String?,
    val lang: String,
    val title: String,
    val date: String,
    val voteAvg: Number,
    val voteCnt: Int,
    val overview : String,
    val page: Int,
    val totalPages: Int
)