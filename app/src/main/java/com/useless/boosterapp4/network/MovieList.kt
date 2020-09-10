package com.useless.boosterapp4.network

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("results")
    var list: ArrayList<MovieResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("page")
    val page: Int
    )

