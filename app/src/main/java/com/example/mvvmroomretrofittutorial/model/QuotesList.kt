package com.example.mvvmroomretrofittutorial.model

import com.example.mvvmroomretrofittutorial.db.ResultData

data class QuotesList(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<ResultData>,
    val totalCount: Int,
    val totalPages: Int
)