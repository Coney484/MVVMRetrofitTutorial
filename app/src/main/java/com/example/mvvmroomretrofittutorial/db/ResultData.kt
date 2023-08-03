package com.example.mvvmroomretrofittutorial.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("quote")
data class ResultData(
    @PrimaryKey(autoGenerate = true)
    val qid: Long,
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
)