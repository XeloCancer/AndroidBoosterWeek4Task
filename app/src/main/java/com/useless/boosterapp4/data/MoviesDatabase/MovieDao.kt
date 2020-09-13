package com.useless.boosterapp4.data.MoviesDatabase
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.useless.boosterapp4.data.models.local.Movie

@Dao
interface MovieDao {
    @Insert
    fun addMovies (movie: Movie)
    @Query("SELECT * FROM movies_table")
    fun getAllMovie(): List<Movie>
}