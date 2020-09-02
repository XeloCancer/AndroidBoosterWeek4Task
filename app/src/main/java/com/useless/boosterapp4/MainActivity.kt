package com.useless.boosterapp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.useless.boosterapp4.network.LocalRepo
import com.useless.boosterapp4.network.Movie
import com.useless.boosterapp4.network.MovieList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LocalRepo.MovieListCallback{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movie_list_recycler_view.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        movie_list_recycler_view.layoutManager = GridLayoutManager(this, 2)

        movie_list_recycler_view.adapter = RecyclerAdapter(null)

        callAPIbtn.setOnClickListener {
            LocalRepo.requestMovieList(this@MainActivity)
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