package com.useless.boosterapp4.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.useless.boosterapp4.network.Movie
import androidx.recyclerview.widget.LinearLayoutManager
import com.useless.boosterapp4.R
import kotlinx.android.synthetic.main.activity_main.*

class MovieRecycler : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movie_list_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        movie_list_recycler_view.layoutManager = GridLayoutManager(this, 2)
       // movie_list_recycler_view.adapter = RecyclerAdapter(getMovies())

    }

}