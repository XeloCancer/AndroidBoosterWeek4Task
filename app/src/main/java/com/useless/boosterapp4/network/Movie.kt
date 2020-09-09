package com.useless.boosterapp4.network

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//Re-check if we need anything else from Movie from the API, we already got the bare minimum for the design.

@Entity(tableName = "movies_table")
data class Movie(
        val posterPath: String ="",
        val lang: String ="",
        val title: String ="",
        val date: String ="",
        val voteAvg: Number,
        val voteCnt: Int,
        val overview : String ="",
        @PrimaryKey
        val id: Int = 123
        )