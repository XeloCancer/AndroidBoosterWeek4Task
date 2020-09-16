package com.useless.boosterapp4.data.network

import com.useless.boosterapp4.data.models.remote.MovieListResponse
import com.useless.boosterapp4.data.models.remote.MovieResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {


    //Gets a list of popular movies, queries: apiKey, Page number, Language
    @GET("movie/popular")
    fun doGetMoviesList(@Query("api_key") apiKey: String,
                        @Query("language") lang: String = "en-US",
                        @Query("page") page: Int = 1
    ): Call<MovieListResponse>

    @GET("movie/{id}")
    fun doGetMovieByID(@Path("id") movieID: Int,
                       @Query("api_key") apiKey: String
    ): Call<MovieResponse>

//    @GET("movie/{movie_id}")
//    fun doGetMovieVideo(@Path("movie_id") movieID: Int,
//                       @Query("api_key") apiKey: String
//    ): Call<MovieResponse>

    @GET("movie/top_rated")
    fun doGetMovieByRate(@Query("api_key")apiKey: String,
                         @Query("page") page: Int = 1
    ): Call<MovieListResponse>
}