package com.useless.boosterapp4.data.repository

import android.content.Context
import android.widget.ProgressBar
import android.widget.Toast
import com.useless.boosterapp4.data.MoviesDatabase.MDatabase
import com.useless.boosterapp4.data.MoviesDatabase.MovieMapper
import com.useless.boosterapp4.data.models.local.Movie
import com.useless.boosterapp4.data.models.remote.MovieListResponse
import com.useless.boosterapp4.data.models.remote.MovieResponse
import com.useless.boosterapp4.data.network.APIClient
import com.useless.boosterapp4.data.network.ApiInterface
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
    private var lastUsedFun: Int = -1
    private lateinit var movieListData: MovieListResponse
    private lateinit var prevMovieListData: MovieListResponse
    private lateinit var movieData: MovieResponse
    private lateinit var movieListLocalData: List<Movie>

    fun requestLastFun(
        callback: MovieListCallback,
        loadingBar: ProgressBar,
        page: Int,
        addInfo: Boolean
    ) {

        when (lastUsedFun) {
            1 -> requestPopularMovieList(callback, page, addInfo)
            2 -> requestTopRatedMovieList(callback, page, addInfo)
        }
    }

    private lateinit var mDatabase: MDatabase
    private val mapper by lazy { MovieMapper() }

    /*fun requestMovieData(callback: MovieCallback, movieID: Int) {
        lastUsedFun = 0
        apiServices.doGetMovieByID(movieID, apiKey)
            .enqueue(object : Callback<MovieResponse> {

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    println("OnResponseCalled")
                    if (response.isSuccessful) {
                        val movieDetail = mapper.mapToMovieUi(response.body()!!)
                        mDatabase.getMovieDao().addMovies(movieDetail)
                        callback.onMovieReady(movieDetail)
                    } else if (response.code() in 400..404) {
                        val msg = "The movies didn't load properly from the API"
                        callback.onMovieError(msg)
                    }
                }
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                    val msg = "Error while getting movie data"
                    callback.onMovieError(msg)
                }
            })
    }*/

    fun requestPopularMovieList(callback: MovieListCallback, page: Int, addInfo: Boolean = false) {
        if(this::movieListData.isInitialized && page > movieListData.totalPages){
            callback.onMovieListError("You're already in the last page")
            return
        }

        if (this::movieListData.isInitialized && lastUsedFun == 1 && !addInfo) {

            callback.onMovieListReady(mDatabase.getMovieDao().getAllMovies(), false)
            return
        }
        lastUsedFun = 1

        //   loadingBar.show()
        apiServices.doGetMoviesList(apiKey, page = page)
            .enqueue(object : Callback<MovieListResponse> {

                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    println("OnResponseCalled")
                    if (response.isSuccessful) {
                        if (!addInfo) {
                            movieListData = response.body()!!
                            movieListLocalData = mapper.mapToMovieListUi(movieListData)
                            movieListLocalData.forEach {
                                mDatabase.getMovieDao().addMovies(it)
                            }
                            callback.onMovieListReady(mDatabase.getMovieDao().getAllMovies(), false)
                        } else if (addInfo) {
//                            prevMovieListData = movieListData
                            movieListData = response.body()!!
//                            prevMovieListData.list.addAll(movieListData.list)
                            movieListLocalData = mapper.mapToMovieListUi(movieListData)
//                            movieListData.list = prevMovieListData.list
                            movieListLocalData.forEach {
                                mDatabase.getMovieDao().addMovies(it)
                            }
                            callback.onMovieListReady(movieListLocalData, true)
                        }
                        //    loadingBar.hide()
                    } else if (response.code() in 400..600) {

                        val msg = "The movies didn't load properly from the API ${response.code()}"
                        callback.onMovieListError(msg)
                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    t.printStackTrace()
                    val msg = "Error while getting movie data ${t.message}"
                    callback.onMovieListError(msg)
                    callback.onMovieListReady(mDatabase.getMovieDao().getAllMovies(), false)
                }
            })

    }


    fun requestTopRatedMovieList(callback: MovieListCallback, page: Int, addInfo: Boolean = false) {
        if(page > movieListData.totalPages){
            callback.onMovieListError("You're already in the last page")
            return
        }

        if (this::movieListData.isInitialized && lastUsedFun == 2 && !addInfo) {

//            callback.onMovieListReady(movieListData)
            return
        }
        lastUsedFun = 2

        // loadingBar.show()
        apiServices.doGetMovieByRate(apiKey, page = page)
            .enqueue(object : Callback<MovieListResponse> {

                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
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
//                        callback.onMovieListReady(movieListData)
                        //   loadingBar.hide()
                    } else if (response.code() in 400..404) {

                        val msg = "The movies didn't load properly from the API ${response.code()}"
                        callback.onMovieListError(msg)
                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    t.printStackTrace()
                    val msg = "Error while getting movie data ${t.message}"
                    callback.onMovieListError(msg)
                }


            })

    }

    fun createDatabase(context: Context) {
        mDatabase = MDatabase.getDatabase(context)
    }

    interface MovieListCallback {
        fun onMovieListReady(movieListData: List<Movie>, addInfo: Boolean)
        fun onMovieListError(errorMsg: String)
    }

    interface MovieCallback {
        fun onMovieReady(movieData: Movie)
        fun onMovieError(errorMsg: String)
    }


}