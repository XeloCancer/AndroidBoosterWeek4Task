package com.useless.boosterapp4.data.MoviesDatabase
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.useless.boosterapp4.data.models.local.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MDatabase: RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    companion object {
        private var INSTANCE: MDatabase? = null

        fun getDatabase(context: Context): MDatabase {

            if (INSTANCE != null)
                return INSTANCE!!

            INSTANCE = Room.databaseBuilder(
                context.applicationContext, MDatabase::class.java, "movie_db"
            ).allowMainThreadQueries().build()

            return INSTANCE!!
        }
    }
}