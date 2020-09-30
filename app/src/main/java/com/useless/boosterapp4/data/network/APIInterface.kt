package com.useless.boosterapp4.data.network

import com.useless.boosterapp4.data.keys.APIKeys
import com.useless.boosterapp4.data.models.remote.MovieListResponse
import com.useless.boosterapp4.data.models.remote.MovieResponse
import com.useless.boosterapp4.data.models.remote.MovieVideos
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    //Gets a list of popular movies, queries: apiKey, Page number, Language
    @GET("movie/popular")
    fun doGetMoviesList(@Query("api_key") apiKey: String = APIKeys.tmdbAPIKey,
                        @Query("language") lang: String = "en-US",
                        @Query("page") page: Int = 1
    ): Call<MovieListResponse>

    @GET("movie/{id}")
    fun doGetMovieByID(@Path("id") movieID: Int,
                       @Query("api_key") apiKey: String = APIKeys.tmdbAPIKey
    ): Call<MovieResponse>

    @GET("movie/top_rated")
    fun doGetMovieByRate(@Query("api_key")apiKey: String = APIKeys.tmdbAPIKey,
                         @Query("page") page: Int = 1
    ): Call<MovieListResponse>

    @GET("movie/{id}/videos")
    fun doGetMovieVideos(@Path("id") movieID: Int,
                         @Query("api_key") apiKey: String = APIKeys.tmdbAPIKey
    ): Call<MovieVideos>
}