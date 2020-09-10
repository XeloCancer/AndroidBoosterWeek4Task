package com.useless.boosterapp4.remote

import com.google.gson.annotations.SerializedName

data class MovieResponse (

@SerializedName("title")
val movie_title: String,
@SerializedName("main")
val movieData: MovieData,
@SerializedName("movie")
val movielist: List<Movieinfo>,
)

data class MovieData(
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

)

data class Movieinfo(
    @SerializedName("title")
    val movie_title: String,
    @SerializedName("overview")
    val overview: String
)