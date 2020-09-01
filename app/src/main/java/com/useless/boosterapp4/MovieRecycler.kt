package com.useless.boosterapp4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.useless.boosterapp4.network.Movie
import androidx.recyclerview.widget.LinearLayoutManager
import com.useless.boosterapp4.network.APIClient

class MovieRecycler : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout."the movie activity layout will be here")

        "RecyclerView id will be here".LayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        "RecyclerView id will be here".LayoutManager = GridLayoutManager(this, 2)
        "RecyclerView id will be here".adapter = RecyclerAdapter(getMovies())

    }

    private fun getMovies(): List<Movie>{
        val moviesList = ArrayList<Movie>()

        moviesList.add(Movie(APIClient.getClient()))

        return moviesList
    }
}