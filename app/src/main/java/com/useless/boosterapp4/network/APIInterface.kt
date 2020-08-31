package com.useless.boosterapp4.network

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("/api/unknown")
    fun doGetMoviesList(apiKey: String): Call<List<Movie?>?>

}