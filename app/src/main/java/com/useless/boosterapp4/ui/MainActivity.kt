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
import androidx.recyclerview.widget.RecyclerView
import com.useless.boosterapp4.R
import com.useless.boosterapp4.data.models.local.Movie
import com.useless.boosterapp4.data.repository.LocalRepo
import com.useless.boosterapp4.data.recyclerData.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    RecyclerAdapter.PageControl {
    private lateinit var colorAnimLightMostPopular : ObjectAnimator
    private lateinit var colorAnimDimMostPopular : ObjectAnimator

    private lateinit var colorAnimLightTopRated : ObjectAnimator
    private lateinit var colorAnimDimTopRated : ObjectAnimator

    private val lightColor : Int = Color.parseColor("#A0A0A0")
    private val dimColor : Int = Color.parseColor("#F0F0F0")

    private var addInfo: Boolean = false
    private var firstTime: Boolean = true
    private lateinit var adapter: RecyclerAdapter

    //private lateinit var moviesAdapter: RecyclerAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var page : Int = 1

    override fun nextPage(page: Int){
        addInfo = true
        println(" THE FIRSTTIME BOOLEAN IS $firstTime")
        movieViewModel.loadMovieData(page, true)

    }
    private val movieViewModel : MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieViewModel.movieLiveData.observe(this, {
            onMovieListReady(it, true)
            println("The data has changed Adel!!")
        })

        movieViewModel.onError.observe(this,{
            onMovieListError(it)
        })

        movieViewModel.loadMovieData(page, false)

        layoutManager = GridLayoutManager(this, 2)

        movie_list_recycler_view.layoutManager = layoutManager


        //TODO To call data on app launch. There is definitely a better way to do this so if you have ideas, please do it

        //Responsible for handling changes to button clicks
        button_group.addOnButtonCheckedListener { group, checkedId, isChecked ->
            page = 1
            when(group.checkedButtonId){
                most_popular_button.id -> {
                    addInfo = false
                    movieViewModel.movieLiveData.observe(this, {
                        onMovieListReady(it, addInfo)
                    })

                    movieViewModel.onError.observe(this,{
                        onMovieListError(it)
                    })
                    firstTime = true
                    movieViewModel.loadMovieData(page, false)

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

                    movieViewModel.loadMovieData(page, false)

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

    }

    fun onMovieListReady(movieListData: List<Movie>, addInfo: Boolean) {
        val listOfMovies: List<Movie> = movieListData
        println("The call back is in the MainActivity dum dum !")
        if(firstTime){
            firstTime = false
            adapter = RecyclerAdapter(
                movieListData,
                listOfMovies,
                this@MainActivity
            )
            Toast.makeText(this@MainActivity, "THE MOVIE LIST IS READY", Toast.LENGTH_LONG).show()
            movie_list_recycler_view.adapter = adapter
        }else if(addInfo){
            adapter.addData(listOfMovies)
        }

        listenForNextPage()
    }

    private fun listenForNextPage(){
        movie_list_recycler_view.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(!movie_list_recycler_view.canScrollVertically(1) && !MovieViewModel.isLoading){
                    nextPage(++page)
                }
            }
        })
    }

    fun onMovieListError(errorMsg: String) {
        Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_LONG).show()
    }

}