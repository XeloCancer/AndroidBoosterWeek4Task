package com.useless.boosterapp4.network

import android.os.CountDownTimer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LocalRepo {
<<<<<<< HEAD
/*
=======

>>>>>>> origin/Lotfy_Recycler_branch
    var seconds: Int = 0

    private val timer = object: CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            seconds = (millisUntilFinished / 1000).toInt()
        }
        override fun onFinish() {
        }
    }
<<<<<<< HEAD
*/
=======

>>>>>>> origin/Lotfy_Recycler_branch
    private val apiServices: ApiInterface by lazy {
        APIClient.getClient().create(ApiInterface::class.java)
    }

    private const val apiKey = "6637f7d017283c784ff6746c01f71453"

<<<<<<< HEAD
    private lateinit var movieListData: MovieList
    private lateinit var movieData: Movie

    fun requestMovieList(callback: MovieListCallback){
        if (this::movieListData.isInitialized) {
=======
    private lateinit var movieListData: List<Movie?>
    private lateinit var movieData: Movie

    fun requestMovieList(callback: MovieListCallback){
        if (this::movieData.isInitialized && seconds != 0) {
>>>>>>> origin/Lotfy_Recycler_branch

            callback.onMovieListReady(movieListData)
            return
        }
<<<<<<< HEAD
        apiServices.doGetMoviesList(apiKey)
            .enqueue(object : Callback<MovieList> {

                override fun onResponse(
                    call: Call<MovieList>,
                    response: Response<MovieList>
=======
        timer.start()
        apiServices.doGetMoviesList(apiKey)
            .enqueue(object : Callback<List<Movie?>?> {

                override fun onResponse(
                    call: Call<List<Movie?>?>,
                    response: Response<List<Movie?>?>
>>>>>>> origin/Lotfy_Recycler_branch
                ) {
                    println("OnResponseCalled")
                    if (response.isSuccessful) {
                        movieListData = response.body()!!
                        callback.onMovieListReady(movieListData)
                    } else if (response.code() in 400..404) {
<<<<<<< HEAD

                        val msg = "The movies didn't load properly from the API ${response.code()}"
=======
                        val msg = "The movies didn't load properly from the API"
>>>>>>> origin/Lotfy_Recycler_branch
                        callback.onMovieListError(msg)
                    }
                }

<<<<<<< HEAD
                override fun onFailure(call: Call<MovieList>, t: Throwable) {
                    t.printStackTrace()
                    val msg = "Error while getting movie data ${t.message}"
=======
                override fun onFailure(call: Call<List<Movie?>?>, t: Throwable) {
                    t.printStackTrace()
                    val msg = "Error while getting movie data"
>>>>>>> origin/Lotfy_Recycler_branch
                    callback.onMovieListError(msg)
                }


            })

    }

    fun requestMovieData(callback: MovieCallback, movieID: Int){
<<<<<<< HEAD
        if (this::movieData.isInitialized) {
            callback.onMovieReady(movieData)
            return
        }
=======
        if (this::movieData.isInitialized && seconds != 0) {
            callback.onMovieReady(movieData)
            return
        }
        timer.start()
>>>>>>> origin/Lotfy_Recycler_branch
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
<<<<<<< HEAD
=======

>>>>>>> origin/Lotfy_Recycler_branch
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    t.printStackTrace()
                    val msg = "Error while getting movie data"
                    callback.onMovieError(msg)
                }
<<<<<<< HEAD
            })
=======


            })

>>>>>>> origin/Lotfy_Recycler_branch
    }


    interface MovieListCallback{
<<<<<<< HEAD
        fun onMovieListReady(movieData: MovieList)
=======
        fun onMovieListReady(movieData: List<Movie?>?)
>>>>>>> origin/Lotfy_Recycler_branch
        fun onMovieListError(errorMsg: String)
    }

    interface MovieCallback{
        fun onMovieReady(movieData: Movie)
        fun onMovieError(errorMsg: String)
    }
}