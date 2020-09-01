package com.useless.boosterapp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Change behavior depending on selected tab
        //https://material.io/develop/android/components/tabs for more information
        //Also check on stackoverflow
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab){
                    latest_tab -> Toast.makeText(this@MainActivity, "Latest Tab Selected", Toast.LENGTH_SHORT).show()
                    most_popular_tab -> Toast.makeText(this@MainActivity, "Most Popular Tab Selected", Toast.LENGTH_SHORT).show()
                    top_rated_tab -> Toast.makeText(this@MainActivity, "Top Rated Selected", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when (tab){
                    latest_tab -> Toast.makeText(this@MainActivity, "Latest Tab Unselected", Toast.LENGTH_SHORT).show()
                    most_popular_tab -> Toast.makeText(this@MainActivity, "Most Popular Tab Unselected", Toast.LENGTH_SHORT).show()
                    top_rated_tab -> Toast.makeText(this@MainActivity, "Top Rated Unselected", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
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
}