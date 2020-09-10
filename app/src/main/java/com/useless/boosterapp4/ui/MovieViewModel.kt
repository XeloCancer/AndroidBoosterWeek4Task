package com.useless.boosterapp4.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.useless.boosterapp4.network.LocalRepo
import com.useless.boosterapp4.network.MovieList

class MovieViewModel : ViewModel(), LocalRepo.MovieListCallback {

    private val _movieLiveData: MutableLiveData<List<Movie>>
            by lazy { MutableLiveData<List<Movie>>() }
    val movieLiveData: LiveData<List<Movie>>
        get() = _movieLiveData

    private val _onError: MutableLiveData<String>
            by lazy { MutableLiveData<String>() }
    val onError: LiveData<String>
        get() = _onError

    private lateinit var movieListData: List<Movie>

    private var currentPage = 1

    fun loadMovieData(page: Int) {
        if (page == currentPage && this::movieListData.isInitialized) {
            _movieLiveData.value = movieListData
            return
        }
        if (page == 1)
            LocalRepo.requestMovieList(this, currentPage)

    }

    override fun onMovieListReady(movieData: List<Movie>) {
        _movieLiveData.value = movieData
    }

    override fun onMovieListError(errorMsg: String) {
        _onError.value = errorMsg
    }
}