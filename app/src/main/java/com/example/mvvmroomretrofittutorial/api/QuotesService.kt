package com.example.mvvmroomretrofittutorial.api

import com.example.mvvmroomretrofittutorial.model.QuotesList
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface QuotesService {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int): Response<QuotesList>
}