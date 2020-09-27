package com.useless.boosterapp4.data.MoviesDatabase
import androidx.room.*
import com.useless.boosterapp4.data.models.local.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies (movie: Movie)
    @Query("SELECT * FROM movies_table")
    fun getAllMovies(): List<Movie>
    @Query("DELETE FROM movies_table")
    fun deleteAllMovies()
    @Query("SELECT * FROM movies_table where id = :movieId")
    fun getMovieFromDao(movieId: Int): Movie
    @Query("select * from movies_table where fav = 1") //TODO: Test if 1 = true logically
    fun getFav(): List<Movie>
}