package com.useless.boosterapp4.network

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {


    //Gets a list of popular movies, queries: apiKey, Page number, Language
    @GET("/movie/popular")
    fun doGetMoviesList(@Query("api_key") apiKey: String, @Query("page") pageNumber: Int = 1, @Query("language") lang: String = "en-US"): Call<List<Movie>>


    @GET("/movie/{id}")
    fun doGetMovieByID(@Path("id") movieID: Int, @Query("api_key") apiKey: String): Call<Movie>
}