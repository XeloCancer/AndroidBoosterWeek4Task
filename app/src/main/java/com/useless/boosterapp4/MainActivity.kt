package com.useless.boosterapp4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.useless.boosterapp4.network.LocalRepo
import com.useless.boosterapp4.network.Movie
import com.useless.boosterapp4.network.MovieList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LocalRepo.MovieListCallback{
    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_POSTER, movie.posterPath)
        intent.putExtra(MOVIE_TITLE, movie.title)
        startActivity(intent)
    }
    lateinit var dtlsMovies: RecyclerView
    lateinit var moviesDetailsAdapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movie_list_recycler_view.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        movie_list_recycler_view.layoutManager = GridLayoutManager(this, 2)

        movie_list_recycler_view.adapter = RecyclerAdapter(null)


        moviesDetailsAdapter = RecyclerAdapter(mutableListOf()){ movie -> showMovieDetails(movie) }
        dtlsMovies = findViewById(R.id.movie_list_recycler_view)
        dtlsMovies.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        dtlsMovies.adapter = moviesDetailsAdapter


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