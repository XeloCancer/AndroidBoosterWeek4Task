package com.useless.boosterapp4

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface moviedao {
    @Insert
    fun addmovie(movie:movieentity)
    @Query("SELECT*FROM movie")
    fun getmovie():movieentity
    @Delete
    fun deletemovie(movie:movieentity)
    @Query("DELETE FROM movie")
    fun deleteAll()
    @Query("SELECT*FROM movie")
    fun getallmovies():List<movieentity>
}