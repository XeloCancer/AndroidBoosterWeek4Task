package com.useless.boosterapp4.ui

import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.useless.boosterapp4.network.LocalRepo
import com.useless.boosterapp4.network.MovieList

class MovieViewModel : ViewModel() , LocalRepo.MovieListCallback {

   private  val _movieLiveData: MutableLiveData<MovieList>
            by lazy { MutableLiveData() }
    val movieLiveData : LiveData<MovieList>
    get() = _movieLiveData

    private val _onError : MutableLiveData<String>
    by lazy { MutableLiveData()}
    val onError : LiveData<String>
    get() = _onError

    private lateinit var movieListData: MovieList

    private var currentPage = 1

    fun loadMovieData(page: Int = 1) {
        if (page == currentPage && this::movieListData.isInitialized){
            _movieLiveData.value = movieListData
        return
    }
//    if (page == 1)
//        LocalRepo.requestMovieList(this, currentPage)
//
    }

    override fun onMovieListReady(movieData: MovieList) {
        movieListData = movieData
        _movieLiveData.value = movieListData
    }

    override fun onMovieListError(errorMsg: String) {
        _onError.value = errorMsg
    }
}