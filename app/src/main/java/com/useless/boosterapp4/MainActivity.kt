package com.useless.boosterapp4

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.useless.boosterapp4.network.LocalRepo
import com.useless.boosterapp4.network.MovieList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LocalRepo.MovieListCallback{

    private lateinit var colorAnimLightMostPopular : ObjectAnimator
    private lateinit var colorAnimDimMostPopular : ObjectAnimator

    private lateinit var colorAnimLightTopRated : ObjectAnimator
    private lateinit var colorAnimDimTopRated : ObjectAnimator

    private val lightColor : Int = Color.parseColor("#A0A0A0")
    private val dimColor : Int = Color.parseColor("#F0F0F0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movie_list_recycler_view.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        movie_list_recycler_view.layoutManager = GridLayoutManager(this, 2)

        movie_list_recycler_view.adapter = RecyclerAdapter(null)

        //TODO To call data on app launch. There is definitely a better way to do this so if you have ideas, please do it
        LocalRepo.requestMovieList(this@MainActivity, loading_bar)

        //Responsible for handling changes to button clicks
        button_group.addOnButtonCheckedListener { group, checkedId, isChecked ->
            when(group.checkedButtonId){
                most_popular_button.id -> {
                    LocalRepo.requestMovieList(this@MainActivity, loading_bar)

                    //To animate color change for different buttons
                    colorAnimLightMostPopular = ObjectAnimator.ofInt(most_popular_button, "textColor", lightColor, dimColor)
                    colorAnimDimMostPopular = ObjectAnimator.ofInt(top_rated_button, "textColor", dimColor, lightColor)
                    colorAnimLightMostPopular.setEvaluator(ArgbEvaluator())
                    colorAnimDimMostPopular.setEvaluator(ArgbEvaluator())
                    colorAnimLightMostPopular.start()
                    colorAnimDimMostPopular.start()
                }
                top_rated_button.id -> {
                    //TODO Adjust this to suit Top Rated API when created
                    LocalRepo.requestMovieList(this@MainActivity, loading_bar)

                    //To animate color change for different buttons
                    colorAnimLightTopRated = ObjectAnimator.ofInt(top_rated_button, "textColor", lightColor, dimColor)
                    colorAnimDimTopRated = ObjectAnimator.ofInt(most_popular_button, "textColor", dimColor, lightColor)
                    colorAnimLightTopRated.setEvaluator(ArgbEvaluator())
                    colorAnimDimTopRated.setEvaluator(ArgbEvaluator())
                    colorAnimLightTopRated.start()
                    colorAnimDimTopRated.start()
                }
            }
        }

    }



    fun nextPage(pageNum: Int, totalPages: Int){
        if(pageNum == totalPages){
            Toast.makeText(applicationContext, "You're already at the last page", Toast.LENGTH_SHORT).show()
            return
        }else{
            //TODO: call API with same data but query page = pageNum + 1
        }
    }
    fun prevPage(pageNum: Int){
        if(pageNum == 1){
            Toast.makeText(applicationContext, "You're already at the first page", Toast.LENGTH_SHORT).show()
            return
        }else{
            //TODO: call API with same data but query page = pageNum - 1
        }
    }

    override fun onMovieListReady(movieData: MovieList) {
        Toast.makeText(this@MainActivity, "THE MOVIE LIST IS READY", Toast.LENGTH_LONG).show()
        movie_list_recycler_view.adapter = RecyclerAdapter(movieData.list)
    }

    override fun onMovieListError(errorMsg: String) {
        Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_LONG).show()
    }

}