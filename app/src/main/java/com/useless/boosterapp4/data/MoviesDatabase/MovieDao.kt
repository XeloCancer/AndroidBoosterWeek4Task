package com.useless.boosterapp4.data.MoviesDatabase
import androidx.room.*
import com.useless.boosterapp4.data.models.local.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies (movie: Movie)
    @Query("SELECT * FROM movies_table")
    fun getAllMovies(): List<Movie>
    @Query("select * from movies_table where isPop = 1")
    fun getPopMovies(): List<Movie>
    @Query("select * from movies_table where isRat = 1")
    fun getRatMovies(): List<Movie>
    @Query("DELETE FROM movies_table")
    fun deleteAllMovies()
    @Query("SELECT * FROM movies_table where id = :movieId")
    fun getMovieFromDao(movieId: Int): Movie?
    @Query("select * from movies_table where fav = 1")
    fun getFav(): List<Movie>
}