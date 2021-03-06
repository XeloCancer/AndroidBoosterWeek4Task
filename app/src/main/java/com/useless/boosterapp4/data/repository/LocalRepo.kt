package com.useless.boosterapp4.data.repository

import android.content.Context
import android.view.View
import com.useless.boosterapp4.data.MoviesDatabase.MDatabase
import com.useless.boosterapp4.data.MoviesDatabase.MovieMapper
import com.useless.boosterapp4.data.models.local.Movie
import com.useless.boosterapp4.data.models.remote.MovieListResponse
import com.useless.boosterapp4.data.models.remote.MovieResponse
import com.useless.boosterapp4.data.models.remote.MovieReview
import com.useless.boosterapp4.data.models.remote.MovieVideos
import com.useless.boosterapp4.data.network.APIClient
import com.useless.boosterapp4.data.network.ApiInterface
import com.useless.boosterapp4.utils.MovieType
import com.useless.boosterapp4.utils.flagAs
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


    private var lastUsedFun: Int = -1
    private lateinit var movieListData: MovieListResponse
    private lateinit var prevMovieListData: MovieListResponse
    private lateinit var movieData: MovieResponse
    private lateinit var movieListLocalData: List<Movie>
    private lateinit var videoData: MovieVideos
    private lateinit var reviewData: MovieReview

    fun requestLastFun(
        callback: MovieListCallback,
        page: Int,
        addInfo: Boolean
    ) {

        when (lastUsedFun) {
            1 -> requestPopularMovieList(callback, page, addInfo)
            2 -> requestTopRatedMovieList(callback, page, addInfo)
            3 -> loadFavList(callback, page, addInfo)
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

    fun requestPopularMovieList(callback: MovieListCallback, page: Int, addInfo: Boolean = false, changing : Boolean = false) {
        if (this::movieListData.isInitialized && page > movieListData.totalPages) {
            callback.onMovieListError("You're already in the last page")
            return
        }

        if (this::movieListData.isInitialized && lastUsedFun == 1 && !addInfo && !changing) {
            callback.onMovieListReady(mDatabase.getMovieDao().getPopMovies().sortedByDescending { it.popularity }, false)
            return
        }
        lastUsedFun = 1

        //   loadingBar.show()
        apiServices.doGetMoviesList( page = page)
            .enqueue(object : Callback<MovieListResponse> {

                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    println("OnResponseCalled")
                    if (response.isSuccessful) {/*
                        if (!mDatabase.getMovieDao().getAllMovies().isNullOrEmpty())
                            mDatabase.getMovieDao().deleteAllMovies()
                            */
                        if (!addInfo) {
                            movieListData = response.body()!!
                            movieListLocalData = mapper.mapToMovieListUi(movieListData)
                            movieListLocalData.forEach {
                                it.flagAs(MovieType.POP)
                                //mDatabase.getMovieDao().addMovies(it) TODO: THIS MIGHT NOT BE NECESSARY AS FLAGAS ALREADY INSERTS MOVIES
                            }
                            callback.onMovieListReady(mDatabase.getMovieDao().getPopMovies().sortedByDescending { it.popularity }, false)
                        } else if (addInfo) {
//                            prevMovieListData = movieListData
                            movieListData = response.body()!!
//                            prevMovieListData.list.addAll(movieListData.list)
                            movieListLocalData = mapper.mapToMovieListUi(movieListData)
//                            movieListData.list = prevMovieListData.list
                            movieListLocalData.forEach {
                                it.flagAs(MovieType.POP)
                                //mDatabase.getMovieDao().addMovies(it) TODO: THIS MIGHT NOT BE NECESSARY AS FLAGAS ALREADY INSERTS MOVIES
                            }
                            println("I am calling back the popular movie list, with addInfo as $addInfo")
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
                    callback.onMovieListReady(mDatabase.getMovieDao().getPopMovies().sortedByDescending { it.popularity }, false)
                }
            })

    }

    fun requestTopRatedMovieList(callback: MovieListCallback, page: Int, addInfo: Boolean = false, changing : Boolean = false) {
        if (this::movieListData.isInitialized && page > movieListData.totalPages) {
            callback.onMovieListError("You're already in the last page")
            return
        }

        if (this::movieListData.isInitialized && lastUsedFun == 1 && !addInfo && !changing) {
            callback.onMovieListReady(mDatabase.getMovieDao().getRatMovies().sortedByDescending { it.voteAvg }, false)
            return
        }
        lastUsedFun = 2

        //   loadingBar.show()
        apiServices.doGetMovieByRate(page = page)
            .enqueue(object : Callback<MovieListResponse> {

                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    println("OnResponseCalled")
                    if (response.isSuccessful) {/*
                            if (!mDatabase.getMovieDao().getAllMovies().isNullOrEmpty()) //TODO: Why are we deleting the database in here ?
                            mDatabase.getMovieDao().deleteAllMovies()
                            */
                        if (!addInfo) {
                            movieListData = response.body()!!
                            movieListLocalData = mapper.mapToMovieListUi(movieListData)
                            movieListLocalData.forEach {
                                it.flagAs(MovieType.RAT)
                                //mDatabase.getMovieDao().addMovies(it) TODO: THIS MIGHT NOT BE NECESSARY AS FLAGAS ALREADY INSERTS MOVIES
                            }
                            callback.onMovieListReady(mDatabase.getMovieDao().getRatMovies().sortedByDescending { it.voteAvg }, false)
                        } else if (addInfo) {
//                            prevMovieListData = movieListData
                            movieListData = response.body()!!
//                            prevMovieListData.list.addAll(movieListData.list)
                            movieListLocalData = mapper.mapToMovieListUi(movieListData)
//                            movieListData.list = prevMovieListData.list
                            movieListLocalData.forEach {
                                it.flagAs(MovieType.RAT)
                                //mDatabase.getMovieDao().addMovies(it) TODO: THIS MIGHT NOT BE NECESSARY AS FLAGAS ALREADY INSERTS MOVIES
                            }
                            println("I am calling back the popular movie list, with addInfo as $addInfo")
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
                    callback.onMovieListReady(mDatabase.getMovieDao().getRatMovies().sortedByDescending { it.voteAvg }, false)
                }
            })
    }

    fun getMovieFromDBase(movieID: Int): Movie? {
        return mDatabase.getMovieDao().getMovieFromDao(movieID)
    }
    fun insertMovie(movie: Movie){
        mDatabase.getMovieDao().addMovies(movie)
    }
    fun loadFavList(callback: MovieListCallback, page: Int = 1, addInfo: Boolean = false){
        lastUsedFun = 3
        if(addInfo){
            return
        }
        callback.onMovieListReady(mDatabase.getMovieDao().getFav().sortedByDescending { it.popularity }, addInfo)
    }

    fun calcPopPage() : Int {
        return mDatabase.getMovieDao().getPopMovies().size / 20
    }

    fun calcRatPage() : Int {
        return mDatabase.getMovieDao().getRatMovies().size / 20
    }

    fun requestMovieVideos(
        callback: MovieVideosCallback,
        movieID: Int,
        itemView: View
    ){
        apiServices.doGetMovieVideos(movieID).enqueue(object: Callback<MovieVideos>{
            override fun onResponse(
                call: Call<MovieVideos>,
                response: Response<MovieVideos>
            ) {
                if(response.isSuccessful){
                    videoData = response.body()!!
                    callback.onMovieVideosReady(videoData, itemView)
                }else if (response.code() in 400..404) {
                    val msg = "The videos didn't load properly from the API"
                    callback.onMovieVideosError(msg, itemView)
                }
            }
            override fun onFailure(call: Call<MovieVideos>, t: Throwable) {
                t.printStackTrace()
                val msg = "Error while getting video data"
                callback.onMovieVideosError(msg, itemView)
            }

        })
    }

    fun requestMovieReviews(
        callback: MovieReviewsCallback,
        movieID: Int
    ){
        apiServices.doGetMovieReviews(movieID).enqueue(object: Callback<MovieReview>{
            override fun onResponse(
                call: Call<MovieReview>,
                response: Response<MovieReview>
            ) {
                if(response.isSuccessful){
                    reviewData = response.body()!!
                    callback.onMovieReviewsReady(reviewData)
                }else if (response.code() in 400..404) {
                    val msg = "The videos didn't load properly from the API"
                    callback.onMovieReviewsError(msg)
                }
            }
            override fun onFailure(call: Call<MovieReview>, t: Throwable) {
                t.printStackTrace()
                val msg = "Error while getting video data"
                callback.onMovieReviewsError(msg)
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

    interface MovieVideosCallback{
        fun onMovieVideosReady(videoData: MovieVideos, itemView: View)
        fun onMovieVideosError(errorMsg: String, itemView: View)
    }

    interface MovieReviewsCallback{
        fun onMovieReviewsReady(reviewData: MovieReview)
        fun onMovieReviewsError(errorMsg: String)
    }

}