package com.useless.boosterapp4.MoviesDatabase
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.useless.boosterapp4.network.Movie

@Dao
interface MovieDao {
    @Insert
    fun addMovies (movies: Movie)
    @Query("SELECT * FROM movies_table")
    fun getAllMovie(): List<Movie>
}