package com.useless.boosterapp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.useless.boosterapp4.network.LocalRepo
import com.useless.boosterapp4.network.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LocalRepo.MovieListCallback{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movie_list_recycler_view.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        movie_list_recycler_view.layoutManager = GridLayoutManager(this, 2)

        movie_list_recycler_view.adapter = RecyclerAdapter(null)

        //Change behavior depending on selected tab
        //https://material.io/develop/android/components/tabs for more information
        //Also check on stackoverflow
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab){
                    latest_tab -> Toast.makeText(this@MainActivity, "Latest Tab Selected", Toast.LENGTH_SHORT).show()
                    most_popular_tab -> {
                        Toast.makeText(this@MainActivity, "Most Popular Tab Selected", Toast.LENGTH_SHORT).show()
                        LocalRepo.requestMovieList(this@MainActivity)
                    }
                    top_rated_tab -> Toast.makeText(this@MainActivity, "Top Rated Selected", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                when (tab){
                    latest_tab -> Toast.makeText(this@MainActivity, "Latest Tab Unselected", Toast.LENGTH_SHORT).show()
                    most_popular_tab -> Toast.makeText(this@MainActivity, "Most Popular Tab Unselected", Toast.LENGTH_SHORT).show()
                    top_rated_tab -> Toast.makeText(this@MainActivity, "Top Rated Unselected", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                when (tab){
                    latest_tab -> Toast.makeText(this@MainActivity, "Latest Tab Selected", Toast.LENGTH_SHORT).show()
                    most_popular_tab -> Toast.makeText(this@MainActivity, "Most Popular Tab Selected", Toast.LENGTH_SHORT).show()
                    top_rated_tab -> Toast.makeText(this@MainActivity, "Top Rated Selected", Toast.LENGTH_SHORT).show()
                }
            }

        })


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

    override fun onMovieListReady(movieData: List<Movie>) {
        Toast.makeText(this@MainActivity, "THIS IS AN ERROR", Toast.LENGTH_LONG).show()
        movie_list_recycler_view.adapter = RecyclerAdapter(movieData)
    }

    override fun onMovieListError(errorMsg: String) {
        Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_LONG).show()
    }
/*
    fun mostPopClicked(view: View){
        latest_tab -> Toast.makeText(this@MainActivity, "Latest Tab Selected", Toast.LENGTH_SHORT).show()
    }
*/

}