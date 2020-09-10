package com.useless.boosterapp4.network

import com.google.gson.annotations.SerializedName

//Re-check if we need anything else from Movie from the API, we already got the bare minimum for the design.

class Movie(
        @SerializedName("id")
        val id: Int,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("original_language")
        val lang: String,
        @SerializedName("original_title")
        val title: String,
        @SerializedName("release_date")
        val date: String,
        @SerializedName("runtime")
        val runtime: Int?,
        @SerializedName("vote_average")
        val voteAvg: Number,
        @SerializedName("vote_count")
        val voteCnt: Int,
        @SerializedName("genres")
        val genres: List<Genre>
        ) {


        data class Genre(
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String
        )

}