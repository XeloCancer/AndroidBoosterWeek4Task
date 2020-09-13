package com.useless.boosterapp4.data.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.useless.boosterapp4.data.models.remote.MovieResponse

@Entity(tableName = "movies_table")
data class MovieList (
    var list: ArrayList<MovieResponse>,
    val totalPages: Int,
    val page: Int,
    @PrimaryKey
    val id: Int = 1234
)