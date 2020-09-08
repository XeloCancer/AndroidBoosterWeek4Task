package com.useless.boosterapp4.ui

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.useless.boosterapp4.R
import com.useless.boosterapp4.RecyclerAdapter
import com.useless.boosterapp4.network.LocalRepo
import com.useless.boosterapp4.network.LocalRepo.requestMovieList
import com.useless.boosterapp4.network.MovieList
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), LocalRepo.MovieListCallback,
    RecyclerAdapter.PageControl {



    private lateinit var colorAnimLightMostPopular : ObjectAnimator
    private lateinit var colorAnimDimMostPopular : ObjectAnimator

    private lateinit var colorAnimLightTopRated : ObjectAnimator
    private lateinit var colorAnimDimTopRated : ObjectAnimator

    private val lightColor : Int = Color.parseColor("#A0A0A0")
    private val dimColor : Int = Color.parseColor("#F0F0F0")

    //private lateinit var moviesAdapter: RecyclerAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var page : Int = 1

    override fun nextPage(movieListData: MovieList){
        var pageNum = movieListData.page
        var totalPages = movieListData.totalPages
        if(pageNum == totalPages){
            Toast.makeText(applicationContext, "You're already at the last page", Toast.LENGTH_SHORT).show()
            return
        }else{
            page++
            LocalRepo.requestLastFun(this@MainActivity, loading_bar, page, true)
        }
    }
    private val movieViewModel : MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     //   requestMovieList(this@MainActivity, page)

        movieViewModel.movieLiveData.observe(this, {
            onMovieListReady(it)
        })

        movieViewModel.onError.observe(this,{
            onMovieListError(it)
        })

        movieViewModel.loadMovieData(page)


           layoutManager = GridLayoutManager(this, 2)

        movie_list_recycler_view.layoutManager = layoutManager

        movie_list_recycler_view.adapter =
            RecyclerAdapter(
                null,
                null,
                this@MainActivity
            )

        //TODO To call data on app launch. There is definitely a better way to do this so if you have ideas, please do it

        //Responsible for handling changes to button clicks
        button_group.addOnButtonCheckedListener { group, checkedId, isChecked ->
            page = 1
            when(group.checkedButtonId){
                most_popular_button.id -> {
                    movieViewModel.movieLiveData.observe(this, {
                        onMovieListReady(it)
                    })

                    movieViewModel.onError.observe(this,{
                        onMovieListError(it)
                    })

                    movieViewModel.loadMovieData(page)

            //    requestMovieList(this@MainActivity, page)
                    //To animate color change for different buttons
                    colorAnimLightMostPopular = ObjectAnimator.ofInt(
                        most_popular_button,
                        "textColor",
                        lightColor,
                        dimColor
                    )
                    colorAnimDimMostPopular = ObjectAnimator.ofInt(
                        top_rated_button,
                        "textColor",
                        dimColor,
                        lightColor
                    )
                    colorAnimLightMostPopular.setEvaluator(ArgbEvaluator())
                    colorAnimDimMostPopular.setEvaluator(ArgbEvaluator())
                    colorAnimLightMostPopular.start()
                    colorAnimDimMostPopular.start()
                }

                top_rated_button.id -> {

                    LocalRepo.requestTopRatedMovieList(this@MainActivity, page)

                    //To animate color change for different buttons
                    colorAnimLightTopRated = ObjectAnimator.ofInt(
                        top_rated_button,
                        "textColor",
                        lightColor,
                        dimColor
                    )
                    colorAnimDimTopRated = ObjectAnimator.ofInt(
                        most_popular_button,
                        "textColor",
                        dimColor,
                        lightColor
                    )
                    colorAnimLightTopRated.setEvaluator(ArgbEvaluator())
                    colorAnimDimTopRated.setEvaluator(ArgbEvaluator())
                    colorAnimLightTopRated.start()
                    colorAnimDimTopRated.start()
                }
            }
        }
/*      COMMENTED TO TEST A MORE STABLE WAY OF SHOWING NEXT PAGE
        movie_list_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.getItemCount()
                    val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()

                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            page++
                            LocalRepo.requestLastFun(this@MainActivity, loading_bar, page, true)
                        }
                }
            }
        })*/

    }



/*
    fun prevPage(pageNum: Int){
        if(pageNum == 1){
            Toast.makeText(applicationContext, "You're already at the first page", Toast.LENGTH_SHORT).show()
            return
        }else{
            //TODO: call API with same data but query page = pageNum - 1
        }
    }
*/
    override fun onMovieListReady(movieData: MovieList) {
        Toast.makeText(this@MainActivity, "THE MOVIE LIST IS READY", Toast.LENGTH_LONG).show()
        movie_list_recycler_view.adapter =
            RecyclerAdapter(
                movieData,
                movieData.list,
                this@MainActivity
            )
    }

    override fun onMovieListError(errorMsg: String) {
        Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_LONG).show()
    }

}