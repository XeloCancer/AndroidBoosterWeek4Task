package com.useless.boosterapp4.network

import com.google.gson.annotations.SerializedName

class MovieList(
    @SerializedName("results")
    var list: ArrayList<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("page")
    val page: Int
    ) {

}