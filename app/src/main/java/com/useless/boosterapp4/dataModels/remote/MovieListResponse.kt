package com.useless.boosterapp4.dataModels.remote

import com.google.gson.annotations.SerializedName
import com.useless.boosterapp4.dataModels.local.Movie

data class MovieListResponse(
    @SerializedName("results")
    var list: ArrayList<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("page")
    val page: Int
    )

