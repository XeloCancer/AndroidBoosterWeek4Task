package com.useless.boosterapp4.dataModels.remote

import com.google.gson.annotations.SerializedName

data class MovieResponse (

    @SerializedName("movie")
val movielist: List<MovieData>,
)

data class MovieData(
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
    @SerializedName("info")
    val movie_info: List<Movieinfo>,

    )

data class Movieinfo(
    @SerializedName("title")
    val movie_title: String,
    @SerializedName("overview")
    val overview: String
)