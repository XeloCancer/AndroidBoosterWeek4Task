package com.useless.boosterapp4.network
import com.google.gson.annotations.SerializedName

class MovieDetails (
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String,
@SerializedName ("overview")
val overview: String
)