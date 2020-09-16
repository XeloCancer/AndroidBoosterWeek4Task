package com.useless.boosterapp4.data.models.remote

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName ("id")
    val id :Int,
    @SerializedName ("poster_path")
    val posterPath: String,
    @SerializedName ("original_language")
    val Lang: String,
    @SerializedName ("release_date")
    val date: String,
    @SerializedName("vote_average")
    val voteAvg: Number,
    @SerializedName("vote_count")
    val voteCnt: Int,
    @SerializedName("title")
    val movie_title: String,
    @SerializedName("overview")
    val overview: String

)