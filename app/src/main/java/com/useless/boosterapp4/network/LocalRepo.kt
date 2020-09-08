package com.useless.boosterapp4.network

import android.os.CountDownTimer
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.useless.boosterapp4.utils.hide
import com.useless.boosterapp4.utils.show
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LocalRepo {
/*
    var seconds: Int = 0

    private val timer = object: CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            seconds = (millisUntilFinished / 1000).toInt()
        }
        override fun onFinish() {
        }
    }
*/
    private val apiServices: ApiInterface by lazy {
        APIClient.getClient().create(ApiInterface::class.java)
    }

    private const val apiKey = "6637f7d017283c784ff6746c01f71453"
    private var lastUsedFun: Int = 4
    private lateinit var movieListData: MovieList
    private lateinit var prevMovieListData: MovieList
    private lateinit var movieData: Movie

    fun requestLastFun(callback: MovieListCallback, loadingBar : ProgressBar, page : Int, addInfo: Boolean){

        when(lastUsedFun){
            0 -> requestMovieList(callback,  page, addInfo)
            2 -> requestTopRatedMovieList(callback, page, addInfo)
        }
    }

    fun requestMovieList(callback: MovieListCallback,  page : Int, addInfo: Boolean = false) {

            if (this::movieListData.isInitialized && lastUsedFun == 0 && !addInfo) {

           callback.onMovieListReady(movieListData)
              return
             }
        lastUsedFun = 0

     //   loadingBar.show()
        apiServices.doGetMoviesList(apiKey, page = page)
            .enqueue(object : Callback<MovieList> {

                override fun onResponse(
                    call: Call<MovieList>,
                    response: Response<MovieList>
                ) {
                    println("OnResponseCalled")
                    if (response.isSuccessful) {
                        if (!addInfo) {
                            movieListData = response.body()!!
                        } else if (addInfo) {
                            prevMovieListData = movieListData
                            movieListData = response.body()!!
                            prevMovieListData.list.addAll(movieListData.list)
                            movieListData.list = prevMovieListData.list
                        }
                        callback.onMovieListReady(movieListData)
                    //    loadingBar.hide()
                    }
                           else if (response.code() in 400..404) {

                             val msg = "The movies didn't load properly from the API ${response.code()}"
                              callback.onMovieListError(msg)
                      }
                }

                override fun onFailure(call: Call<MovieList>, t: Throwable) {
                    t.printStackTrace()
                    val msg = "Error while getting movie data ${t.message}"
                    callback.onMovieListError(msg)
                }
            })

       }


    fun requestMovieData(callback: MovieCallback, movieID: Int){
        if (this::movieData.isInitialized && lastUsedFun == 1) {
            callback.onMovieReady(movieData)
            return
        }
        lastUsedFun = 1
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

    fun requestTopRatedMovieList(callback: MovieListCallback,  page : Int, addInfo: Boolean = false){
        if (this::movieListData.isInitialized && lastUsedFun == 2 && !addInfo) {

            callback.onMovieListReady(movieListData)
            return
        }
        lastUsedFun = 2

       // loadingBar.show()
        apiServices.doGetMovieByRate(apiKey, page = page)
            .enqueue(object : Callback<MovieList> {

                override fun onResponse(
                    call: Call<MovieList>,
                    response: Response<MovieList>
                ) {
                    println("OnResponseCalled")
                    if (response.isSuccessful) {
                        if(!addInfo){
                            movieListData = response.body()!!
                        }else if(addInfo){
                            prevMovieListData = movieListData
                            movieListData = response.body()!!
                            prevMovieListData.list.addAll(movieListData.list)
                            movieListData.list = prevMovieListData.list
                        }
                        callback.onMovieListReady(movieListData)
                     //   loadingBar.hide()
                    } else if (response.code() in 400..404) {

                        val msg = "The movies didn't load properly from the API ${response.code()}"
                        callback.onMovieListError(msg)
                    }
                }

                override fun onFailure(call: Call<MovieList>, t: Throwable) {
                    t.printStackTrace()
                    val msg = "Error while getting movie data ${t.message}"
                    callback.onMovieListError(msg)
                }


            })

    }


    interface MovieListCallback{
        fun onMovieListReady(movieData: MovieList)
        fun onMovieListError(errorMsg: String)
    }

    interface MovieCallback{
        fun onMovieReady(movieData: Movie)
        fun onMovieError(errorMsg: String)
    }
}