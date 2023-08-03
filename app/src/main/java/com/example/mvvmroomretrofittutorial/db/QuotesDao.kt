package com.example.mvvmroomretrofittutorial.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuotesDao {

    @Insert
    suspend fun insertQuotes(quotes: List<ResultData>)

    @Query("SELECT * FROM quote")
    fun getAllQuotes(): List<ResultData>
}