package com.useless.boosterapp4.data.MoviesDatabase
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.useless.boosterapp4.data.models.local.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies (movie: Movie)
    @Query("SELECT * FROM movies_table")
    fun getAllMovies(): List<Movie>
}