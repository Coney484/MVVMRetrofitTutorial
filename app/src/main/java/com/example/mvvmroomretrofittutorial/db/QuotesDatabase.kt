package com.example.mvvmroomretrofittutorial.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ResultData::class], version = 1)
abstract class QuotesDatabase : RoomDatabase() {

    abstract fun quotesDao(): QuotesDao

    companion object {
        private var INSTANCE: QuotesDatabase? = null

        fun getInstance(context: Context): QuotesDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, QuotesDatabase::class.java, "quotes_db")
                            .build()

                }

            }
            return INSTANCE!!
        }
    }
}