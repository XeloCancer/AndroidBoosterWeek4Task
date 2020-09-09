package com.useless.boosterapp4
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface movielistdao {
    @Insert
    fun addmovielist(movie:movielistentity)
    @Query("SELECT*FROM movielist")
    fun getmovielist():movielistentity
    @Delete
    fun deletemovielist(movie:movielistentity)
    @Query("DELETE FROM movielist")
    fun deleteAll()
    @Query("SELECT*FROM movielist")
    fun getallmovielists():List<movielistentity>
}