package com.useless.boosterapp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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