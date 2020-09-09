package com.useless.boosterapp4
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName="movie")
data class `movieentity` (
    @PrimaryKey
    //@SerializedName("id")
    val id: Int,
   // @SerializedName("poster_path")
    val posterPath: String?,
    //@SerializedName("original_language")
    val lang: String,
    //@SerializedName("title")
    val title: String,
    //@SerializedName("release_date")
    val date: String,
    //@SerializedName("vote_average")
    val voteAvg: Number,
    //@SerializedName("vote_count")
    val voteCnt: Int,
    //@SerializedName("overview")
    val overview : String,
)



