package com.useless.boosterapp4.data.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

//Re-check if we need anything else from Movie from the API, we already got the bare minimum for the design.

@Entity(tableName = "movies_table", indices = [Index(value = ["title", "posterPath"], unique = true)])
//@Entity(tableName = "movies_table")
data class Movie(
//    @PrimaryKey(autoGenerate = true)
//        val dbID : Int? = null,
    @PrimaryKey
    val id: Int = 123,
    @ColumnInfo(name = "title")
        val title: String ="",
    @ColumnInfo(name = "posterPath")
        val posterPath: String ="",
    val lang: String ="",
    val vidLink: String="",
    val date: String ="",
    val voteAvg: Double,
    val popularity: Double,
    val voteCnt: Int,
    val overview : String ="",
    val page : Int,
    val totalPages : Int,
    var fav : Boolean = false,
    var isPop: Boolean = false,
    var isRat: Boolean = false
)