package com.useless.boosterapp4.ui

import android.icu.text.CaseMap
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.useless.boosterapp4.network.LocalRepo
import com.useless.boosterapp4.network.Movie

class MovieViewModel : ViewModel()  {


  fun loadMovieData (title: String = "") : LiveData<Movie> {
    if (title.isEmpty())
      LocalRepo.requestMovieList()
    else
      LocalRepo.requestMovieList()
  }

}