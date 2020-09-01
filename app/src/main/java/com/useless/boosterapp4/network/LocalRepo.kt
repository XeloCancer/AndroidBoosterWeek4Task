package com.useless.boosterapp4.network

import android.os.CountDownTimer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LocalRepo {

    var seconds: Int = 0

    private val timer = object: CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            seconds = (millisUntilFinished / 1000).toInt()
        }
        override fun onFinish() {
        }
    }

    private val apiServices: ApiInterface by lazy {
        APIClient.getClient().create(ApiInterface::class.java)
    }

    private const val apiKey = "6637f7d017283c784ff6746c01f71453"

    private lateinit var movieListData: List<Movie>
    private lateinit var movieData: Movie

    fun requestMovieList(callback: MovieListCallback){
        if (this::movieData.isInitialized && seconds != 0) {

            callback.onMovieListReady(movieListData)
            return
        }
        timer.start()
        apiServices.doGetMoviesList(apiKey)
            .enqueue(object : Callback<List<Movie>> {

                override fun onResponse(
                    call: Call<List<Movie>>,
                    response: Response<List<Movie>>
                ) {
                    println("OnResponseCalled")
                    if (response.isSuccessful) {
                        movieListData = response.body()!!
                        callback.onMovieListReady(movieListData)
                    } else if (response.code() in 400..404) {
                        val msg = "The movies didn't load properly from the API"
                        callback.onMovieListError(msg)
                    }
                }

                override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                    t.printStackTrace()
                    val msg = "Error while getting movie data"
                    callback.onMovieListError(msg)
                }


            })

    }

    fun requestMovieData(callback: MovieCallback, movieID: Int){
        if (this::movieData.isInitialized && seconds != 0) {
            callback.onMovieReady(movieData)
            return
        }
        timer.start()
        apiServices.doGetMovieByID(movieID, apiKey)
            .enqueue(object : Callback<Movie> {

                override fun onResponse(
                    call: Call<Movie>,
                    response: Response<Movie>
                ) {
                    println("OnResponseCalled")
                    if (response.isSuccessful) {
                        movieData = response.body()!!
                        callback.onMovieReady(movieData)
                    } else if (response.code() in 400..404) {
                        val msg = "The movies didn't load properly from the API"
                        callback.onMovieError(msg)
                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    t.printStackTrace()
                    val msg = "Error while getting movie data"
                    callback.onMovieError(msg)
                }


            })

    }


    interface MovieListCallback{
        fun onMovieListReady(movieData: List<Movie>)
        fun onMovieListError(errorMsg: String)
    }

    interface MovieCallback{
        fun onMovieReady(movieData: Movie)
        fun onMovieError(errorMsg: String)
    }
}