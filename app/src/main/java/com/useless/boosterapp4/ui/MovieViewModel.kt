package com.useless.boosterapp4.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.useless.boosterapp4.data.repository.LocalRepo
import com.useless.boosterapp4.data.models.local.Movie
import com.useless.boosterapp4.data.recyclerData.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MovieViewModel (application: Application): AndroidViewModel(application),
    LocalRepo.MovieListCallback, LocalRepo.MovieCallback {

    companion object{
        var isLoading = false
    }
    private  val _movieLiveData: MutableLiveData<List<Movie>>
            by lazy { MutableLiveData<List<Movie>>() }
    val movieLiveData : LiveData<List<Movie>>
    get() = _movieLiveData

    private val _onError : MutableLiveData<String>
    by lazy { MutableLiveData<String>() }
    val onError : LiveData<String>
    get() = _onError

    private val _movieDetail: MutableLiveData <Movie>
            by lazy { MutableLiveData <Movie>() }
    val movieDetail: MutableLiveData<Movie>
        get() = _movieDetail

    private lateinit var movieListData: List<Movie>

    private var currentPage = 1
    init {
        LocalRepo.createDatabase(application)
    }

    fun loadMovieData(page: Int, nextPage: Boolean, popular : Boolean = true, changing : Boolean = false) {
        isLoading = true
        if(!nextPage){
            if (page == currentPage && this::movieListData.isInitialized && !changing){
                _movieLiveData.value = movieListData
                return
            }
            if (page == 1){
                if(popular)
                    LocalRepo.requestPopularMovieList (this@MovieViewModel, currentPage, changing)
                else
                    LocalRepo.requestTopRatedMovieList(this@MovieViewModel, currentPage, changing)
            }
        }
        else{
            nextPage(page)
        }
    }

    fun getFavList(){
        LocalRepo.loadFavList(this@MovieViewModel)
    }

    private fun nextPage(page: Int) {
        LocalRepo.requestLastFun(this@MovieViewModel, page, true)
    }

    override fun onMovieListReady(movieListData: List<Movie>, addInfo: Boolean) {
        isLoading = false
        println("the isLoading parameter is here and it's $isLoading")
        this.movieListData = movieListData
        _movieLiveData.value = this.movieListData
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

    interface PageControl{
        fun nextPage(movieList: List<Movie>)
    }

}