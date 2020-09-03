package com.useless.boosterapp4.network

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {


    //Gets a list of popular movies, queries: apiKey, Page number, Language
    @GET("movie/popular")
    fun doGetMoviesList(@Query("api_key") apiKey: String, @Query("language") lang: String = "en-US", @Query("page") page: Int = 1): Call<MovieList>


    @GET("movie/{id}")
    fun doGetMovieByID(@Path("logoPath") movieID: Int, @Query("api_key") apiKey: String): Call<Movie>
}