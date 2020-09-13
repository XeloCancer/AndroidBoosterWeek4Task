package com.useless.boosterapp4.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.useless.boosterapp4.data.repository.LocalRepo
import com.useless.boosterapp4.data.models.local.Movie
import com.useless.boosterapp4.data.models.remote.MovieListResponse

class MovieViewModel (application: Application): AndroidViewModel(application) , LocalRepo.MovieListCallback,
    LocalRepo.MovieCallback {

   private  val _movieLiveData: MutableLiveData<MovieListResponse>
            by lazy { MutableLiveData<MovieListResponse>() }
    val movieLiveData : LiveData<MovieListResponse>
    get() = _movieLiveData

    private val _onError : MutableLiveData<String>
    by lazy { MutableLiveData<String>() }
    val onError : LiveData<String>
    get() = _onError

    private val _movieDetail: MutableLiveData <Movie>
            by lazy { MutableLiveData <Movie>() }
    val movieDetail: MutableLiveData<Movie>
        get() = _movieDetail

    private lateinit var movieListData: MovieListResponse

    private var currentPage = 1
    init {
        LocalRepo.createDatabase(application)
    }

    fun loadMovieData(page: Int) {
        if (page == currentPage && this::movieListData.isInitialized){
            _movieLiveData.value = movieListData
        return
        }
    }

    override fun onMovieListReady(movieData: MovieListResponse) {
        movieListData = movieData
        _movieLiveData.value = movieListData
    }

    override fun onMovieListError(errorMsg: String) {
        _onError.value = errorMsg
    }

    override fun onMovieReady(movieData: Movie) {
      _movieDetail.value = movieData
    }

    override fun onMovieError(errorMsg: String) {
        _onError.value = errorMsg
    }


}