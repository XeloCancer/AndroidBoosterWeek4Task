package com.useless.boosterapp4.network

import com.google.gson.annotations.SerializedName

//Re-check if we need anything else from Movie from the API, we already got the bare minimum for the design.

data class MovieResponse(
        @SerializedName("id")
        val id: Int,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("original_language")
        val lang: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("release_date")
        val date: String,
        @SerializedName("vote_average")
        val voteAvg: Number,
        @SerializedName("vote_count")
        val voteCnt: Int,
        @SerializedName("overview")
        val overview : String,
        )