package com.useless.boosterapp4.network

import com.google.gson.annotations.SerializedName

data class MovieVideos(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val data: List<Video>
)

data class Video(
    @SerializedName("id")
    val id: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("size") //ALLOWED VALUES 360, 480, 720, 1080
    val size: Int,
    @SerializedName("type") //ALLOWED VALUES Trailed, Teaser, Clip, Featurette, Behind the Scenes, Bloopers
    val type: String
)