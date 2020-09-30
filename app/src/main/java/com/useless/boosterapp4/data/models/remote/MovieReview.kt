package com.useless.boosterapp4.data.models.remote

import com.google.gson.annotations.SerializedName

data class MovieReview (
    @SerializedName("results")
    val data: List<ReviewData>
)

data class ReviewData(
    @SerializedName("id")
    val id: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String
)