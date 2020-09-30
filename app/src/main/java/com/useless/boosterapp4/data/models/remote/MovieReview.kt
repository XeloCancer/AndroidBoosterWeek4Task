package com.useless.boosterapp4.data.models.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class MovieReview (
    @SerializedName("results")
    val data: ArrayList<ReviewData>?
)
@Parcelize
data class ReviewData(
    @SerializedName("id")
    val id: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String
) : Parcelable