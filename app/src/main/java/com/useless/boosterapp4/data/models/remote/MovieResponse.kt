package com.useless.boosterapp4.data.models.remote

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName ("id")
    val id :Int,
    @SerializedName ("posterPath")
    val posterPath: String,
    @SerializedName ("lang")
    val Lang: String,
    @SerializedName ("date")
    val date: String,
    @SerializedName("voteAvg")
    val voteAvg: Number,
    @SerializedName("voteCnt")
    val voteCnt: Int,
    @SerializedName("title")
    val movie_title: String,
    @SerializedName("overview")
    val overview: String
)