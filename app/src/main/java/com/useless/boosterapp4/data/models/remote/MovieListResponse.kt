package com.useless.boosterapp4.data.models.remote

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("results")
    var list: ArrayList<MovieResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("page")
    val page: Int
    )

