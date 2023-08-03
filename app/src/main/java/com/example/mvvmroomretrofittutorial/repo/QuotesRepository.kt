package com.example.mvvmroomretrofittutorial.repo

import com.example.mvvmroomretrofittutorial.api.QuotesService
import com.example.mvvmroomretrofittutorial.model.QuotesList
import retrofit2.Response

class QuotesRepository(private val quotesService: QuotesService) {

    suspend fun getQuotes(pageNumber: Int): Response<QuotesList> {
        return quotesService.getQuotes(pageNumber)
    }
}